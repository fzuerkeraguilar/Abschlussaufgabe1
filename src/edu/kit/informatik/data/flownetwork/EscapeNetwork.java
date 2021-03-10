package edu.kit.informatik.data.flownetwork;

import edu.kit.informatik.data.resources.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Model of an escape Network, with identifiers for nodes
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class EscapeNetwork extends FlowNetwork implements Comparable<EscapeNetwork> {
    private final HashMap<String, Integer> nodeNameTable;
    private final List<CapacityResult> capacityResultList;
    private final String identifier;

    /**
     *
     * @param edges list of escape section that represent the inputs
     * @param identifier given identifier of this network
     * @throws NotAValidEscapeNetworkException if given List of escape section does not result in a valid network
     * @throws NoLongerAValidEscapeNetworkException if an escape section would make the network invalid
     */
    public EscapeNetwork(List<Edge> edges, String identifier) throws NotAValidEscapeNetworkException,
            NoLongerAValidEscapeNetworkException {
        super();
        this.identifier = identifier;
        this.nodeNameTable = new HashMap<>();
        this.capacityResultList = new ArrayList<>();
        for (Edge e: edges) {
            //checks if edge is a loop
            if (e.getOrigin().identifier.equals(e.getDestination().identifier)) {
                throw new NoLongerAValidEscapeNetworkException(e.toString());
            }
            //checks if Nodes of edge already exist and assigns it an index
            //if origin Node is not found it is added to the Map
            if (this.nodeNameTable.containsKey(e.getOrigin().identifier)) {
                e.getOrigin().setIndex(this.nodeNameTable.get(e.getOrigin().identifier));
            } else {
                e.getOrigin().setIndex(this.nodes);
                this.adjArrayList.add(this.nodes, new ArrayList<>());
                this.nodeNameTable.put(e.getOrigin().identifier, this.nodes);
                this.nodes++;
            }
            if (this.nodeNameTable.containsKey(e.getDestination().identifier)) {
                e.getDestination().setIndex(this.nodeNameTable.get(e.getDestination().identifier));
            } else {
                e.getDestination().setIndex(this.nodes);
                this.adjArrayList.add(this.nodes, new ArrayList<>());
                this.nodeNameTable.put(e.getDestination().identifier, this.nodes);
                this.nodes++;
            }

            //checks if an escape section in same direction already exists
            for (Edge edge: this.adjArrayList.get(e.getOrigin().getIndex())) {
                if (e.getOrigin().getIndex() == edge.getOrigin().getIndex()
                        && e.getDestination().getIndex() == edge.getDestination().getIndex()) {
                    throw new NoLongerAValidEscapeNetworkException(e.toString());
                }
            }
            //checks if an escape section in opposing direction already exists
            for (Edge edge: this.adjArrayList.get(e.getDestination().getIndex())) {
                if (e.getOrigin().getIndex() == edge.getDestination().getIndex()
                        && e.getDestination().getIndex() == edge.getOrigin().getIndex()) {
                    throw new NoLongerAValidEscapeNetworkException(e.toString());
                }
            }
            this.adjArrayList.get(e.getOrigin().getIndex()).add(e);
        }
        if (this.networkNotValid()) throw new NotAValidEscapeNetworkException(identifier);
    }

    /**
     * Adds new escape section with given capacity for given source and origin node or updates capacity of
     * existing escape section
     * @param newEdge E
     * @throws NoLongerAValidEscapeNetworkException - if new section would make escape network invalid
     * @throws SectionInOpposingDirectionException - if a escape section in opposing direction already exists
     * @throws SameOriginAndDestinationException - if given escape section has same origin and destination
     */
    public void addEscapeSection(Edge newEdge) throws
            NoLongerAValidEscapeNetworkException,
            SectionInOpposingDirectionException,
            SameOriginAndDestinationException,
            IdentifierNotFoundException {
        if (newEdge.getOrigin().identifier.equals(newEdge.getDestination().identifier)) {
            throw new SameOriginAndDestinationException();
        }
        boolean newOriginNode =  !this.nodeNameTable.containsKey(newEdge.getOrigin().identifier);
        boolean newDestinationNode = !this.nodeNameTable.containsKey(newEdge.getDestination().identifier);
        //Checks if a sink and source node with give identifiers are already in use
        if (newOriginNode) {
            this.addNode(newEdge.getOrigin().identifier);
            if (newDestinationNode) {
                this.addNode(newEdge.getDestination().identifier);
            }
        } else {
            if (newDestinationNode) {
                this.addNode(newEdge.getDestination().identifier);
            } else {
                for (Edge e: this.adjArrayList.get(this.convertName(newEdge.getDestination().identifier))) {
                    if (e.getDestination().identifier.equals(newEdge.getOrigin().identifier)) {
                        throw new SectionInOpposingDirectionException();
                    }
                }
            }
        }
        newEdge.getOrigin().setIndex(this.convertName(newEdge.getOrigin().identifier));
        newEdge.getDestination().setIndex(this.convertName(newEdge.getDestination().identifier));
        this.addEdge(newEdge);
        this.capacityResultList.clear();
    }

    /**
     * Looks up if result already calculated or needs to be calculated
     * returns capacity of escape network for given source and sink
     * @param sourceIdentifier identifier of source
     * @param sinkIdentifier identifier of sink
     * @return max capacity of escape network
     */
    public long computeCapacity(String sourceIdentifier, String sinkIdentifier) throws DatabaseException {
        for (CapacityResult r : this.capacityResultList) {
            if (r.source.identifier.equals(sourceIdentifier) && r.sink.identifier.equals(sinkIdentifier)) {
                return r.flowRate;
            }
        }
        if (!this.setSourceIndex(this.convertName(sourceIdentifier))) {
            throw new NotAValidSourceException(sourceIdentifier);
        }
        if (!this.setSinkIndex(this.convertName(sinkIdentifier))) throw new NotAValidSinkException(sinkIdentifier);
        long capacity = this.getCapacity();
        CapacityResult newCapacityResult = new CapacityResult(sourceIdentifier, sinkIdentifier, capacity);
        this.capacityResultList.add(newCapacityResult);
        return capacity;
    }

    /**
     * Returns String representation of given EscapeNetwork
     * Escape ways are primarily sorted by the identifier of their origin
     * If multiple escape ways have the same origin, they are sorted by the identifier of their destination
     * @return each escape way sorted line by line
     */
    @Override
    public String toString() {
        ArrayList<Edge> tempSortEdges = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        for (List<Edge> a : this.adjArrayList) {
            tempSortEdges.addAll(a);
        }
        Collections.sort(tempSortEdges);
        for (Edge e: tempSortEdges) {
            output.append(e.toString());
            output.append(System.lineSeparator());
        }
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

    @Override
    public int compareTo(final EscapeNetwork n) {
        if (this.nodes == n.nodes) {
            return this.identifier.compareTo(n.identifier);
        } else {
            return Integer.compare(n.nodes, this.nodes);
        }
    }

    /**
     * Getter for identifier of network
     * @return identifier of network
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Getter for all already calculated results of this network
     * @return list of all already calculated results of this network
     */
    public List<CapacityResult> getResultList() {
        return this.capacityResultList;
    }

    private int convertName(final String identifier) throws IdentifierNotFoundException {
        if (this.nodeNameTable.get(identifier) == null) throw new IdentifierNotFoundException(identifier);
        return this.nodeNameTable.get(identifier);
    }

    private void addNode(String identifier) {
        this.nodeNameTable.put(identifier, this.nodes);
        this.nodes++;
        this.adjArrayList.add(new ArrayList<>());
    }
}