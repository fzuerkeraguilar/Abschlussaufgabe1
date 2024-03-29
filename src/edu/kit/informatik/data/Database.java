package edu.kit.informatik.data;

import edu.kit.informatik.data.flownetwork.Edge;
import edu.kit.informatik.data.flownetwork.EscapeNetwork;
import edu.kit.informatik.data.flownetwork.CapacityResult;
import edu.kit.informatik.data.resources.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Database class that stores all escape networks and handles the command that needs to be executed
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class Database {
    private final HashMap<String, EscapeNetwork> escapeNetworkTable = new HashMap<>();

    /**
     * Adds EscapeNetwork to this database, and stores it with its Identifier
     * @param networkIdentifier Identifier under which the network is going to be accessed
     * @param edges List of all Edges that this new network should have
     */
    public void addEscapeNetwork(final String networkIdentifier,
                                 final List<Edge> edges) throws IdentifierAlreadyInUseException,
            NotAValidEscapeNetworkException, NoLongerAValidEscapeNetworkException {
        if (this.escapeNetworkTable.containsKey(networkIdentifier)) {
            throw new IdentifierAlreadyInUseException(networkIdentifier);
        }
        EscapeNetwork temp = new EscapeNetwork(edges, networkIdentifier);
        this.escapeNetworkTable.put(networkIdentifier, temp);
    }

    /**
     * To be used when an escape section needs to be added to an already existing network
     * @param networkIdentifier identifier of an already existing network
     * @param newEscapeSection Escape section to be added
     * @throws DatabaseException when problem found
     */
    public void addNewEscapeSection(final String networkIdentifier,
                                    final Edge newEscapeSection) throws DatabaseException {
        this.checkNetworkIdentifier(networkIdentifier);
        this.escapeNetworkTable.get(networkIdentifier).addEscapeSection(newEscapeSection);
    }

    /**
     * Searches database for escape network with given identifier, and returns its maximum capacity for given identifier
     * of a source and sink
     * @param networkIdentifier Identifier of escape network as given during its initialisation
     * @param sourceIdentifier Identifier of source of flow as given during its initialisation
     * @param sinkIdentifier Identifier of sink of flow as given during its initialisation
     * @return The maximum flow rate possible from source to sink
     * @throws DatabaseException when problem found
     */
    public long getCapacity(final String networkIdentifier,
                            final String sourceIdentifier,
                            final String sinkIdentifier) throws DatabaseException {
        this.checkNetworkIdentifier(networkIdentifier);
        return this.escapeNetworkTable.get(networkIdentifier).computeCapacity(sourceIdentifier, sinkIdentifier);
    }

    /**
     * Searches database for escape network with given identifier, and returns its String representation, when found
     * @param networkIdentifier Identifier of escape network as given during its initialisation
     * @return String representation of EscapeNetwork with given Identifier
     * @throws IdentifierNotFoundException if Identifier could not be found in Database
     */
    public String networkToString(String networkIdentifier) throws IdentifierNotFoundException {
        this.checkNetworkIdentifier(networkIdentifier);
        return this.escapeNetworkTable.get(networkIdentifier).toString();
    }

    /**
     * Networks are primarily sorted in descending order of their number of nodes
     * If multiple network have the same number of nodes, they are sorted by their identifier
     * @return all escape networks sorted line by line or {@code null} if no escape networks exist
     */
    public String allNetworksToString() {
        List<EscapeNetwork> networks = new ArrayList<>(this.escapeNetworkTable.values());
        if (networks.size() == 0) {
            return null;
        }
        Collections.sort(networks);
        StringBuilder networkLister = new StringBuilder();
        for (EscapeNetwork n: networks) {
            networkLister.append(n.getIdentifier());
            networkLister.append(" ");
            networkLister.append(n.getNumberOfNodes());
            networkLister.append(System.lineSeparator());
        }
        return networkLister.substring(0, networkLister.length() - 1);
    }

    /**
     * Gives all calculated results of escape network with given identifier
     * @param networkIdentifier identifier of network
     * @return List of all calculated results
     * @throws DatabaseException when problem is found
     */
    public List<CapacityResult> getResults(String networkIdentifier) throws DatabaseException {
        this.checkNetworkIdentifier(networkIdentifier);
        return this.escapeNetworkTable.get(networkIdentifier).getResultList();
    }


    private void checkNetworkIdentifier(String networkIdentifier) throws IdentifierNotFoundException {
        if (!this.escapeNetworkTable.containsKey(networkIdentifier)) {
            throw new IdentifierNotFoundException(networkIdentifier);
        }
    }


}
