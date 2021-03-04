package edu.kit.informatik.data.flownetwork;

import edu.kit.informatik.data.resources.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Model of an escape Network, with identifiers for nodes
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class EscapeNetwork extends FlowNetwork implements Comparable<EscapeNetwork> {
    private final HashMap<String, Integer> nodeNameTable;
    private final ArrayList<CapacityResult> capacityResultList;
    private final String identifier;

    /**
     *
     * @param edges list of escape section that represent the inputs
     * @param identifier given identifier of this network
     * @throws NotAValidEscapeNetworkException if given List of escape section does not result in a valid network
     * @throws NoLongerAValidEscapeNetworkException if an escape section would make the network invalid
     * @throws IdentifierAlreadyInUseException if an escape section is added twice
     */
    public EscapeNetwork(ArrayList<Edge> edges, String identifier) throws NotAValidEscapeNetworkException,
            NoLongerAValidEscapeNetworkException,
            IdentifierAlreadyInUseException {
        super();
        this.identifier = identifier;
        this.nodeNameTable = new HashMap<>();
        this.capacityResultList = new ArrayList<>();
        for (Edge e: edges) {
            if (e.getOriginIdentifier().equals(e.getDestIdentifier())) {
                throw new NoLongerAValidEscapeNetworkException(e.toString());
            }
            //checks if origin Node already exist, gives it an index and stores its name, when not
            if (this.nodeNameTable.containsKey(e.getOriginIdentifier())) {
                e.getOrigin().setIndex(this.nodeNameTable.get(e.getOriginIdentifier()));
            } else {
                e.getOrigin().setIndex(this.nodes);
                this.adjArrayList.add(this.nodes, new ArrayList<>());
                this.nodeNameTable.put(e.getOriginIdentifier(), this.nodes);
                this.nodes++;
            }
            //checks if destination Node already exists, gives it an index and stores its name, when not
            if (this.nodeNameTable.containsKey(e.getDestIdentifier())) {
                e.getDestination().setIndex(this.nodeNameTable.get(e.getDestIdentifier()));
            } else {
                e.getDestination().setIndex(this.nodes);
                this.adjArrayList.add(this.nodes, new ArrayList<>());
                this.nodeNameTable.put(e.getDestIdentifier(), this.nodes);
                this.nodes++;
            }

            //checks if an escape section in same direction already exists
            for (Edge edge: this.adjArrayList.get(e.getOriginIndex())) {
                if (e.getOriginIndex() == edge.getOriginIndex() && e.getDestIndex() == edge.getDestIndex()) {
                    throw new IdentifierAlreadyInUseException(edge.toString());
                }
            }
            //checks if an escape section in opposing direction already exists
            for (Edge edge: this.adjArrayList.get(e.getDestIndex())) {
                if (e.getOriginIndex() == edge.getDestIndex() && e.getDestIndex() == edge.getOriginIndex()) {
                    throw new NoLongerAValidEscapeNetworkException(e.toString());
                }
            }
            this.adjArrayList.get(e.getOriginIndex()).add(e);
        }
        if (this.networkNotValid()) throw new NotAValidEscapeNetworkException(identifier);
    }

    /**
     * Adds new escape section with given capacity for given source and origin node or updates capacity of
     * existing escape section
     * @param origin name of origin node
     * @param destination  name of destination node
     * @param capacity capacity of Section between origin and destination node
     * @throws NoLongerAValidEscapeNetworkException - if new section would make escape network invalid
     * @throws SectionInOpposingDirectionException - if a escape section in opposing direction already exists
     * @throws SameOriginAndDestinationException - if given escape section has same origin and destination
     */
    public void addEscapeSection(String origin, String destination, int capacity) throws
            NoLongerAValidEscapeNetworkException,
            SectionInOpposingDirectionException,
            SameOriginAndDestinationException,
            IdentifierNotFoundException {
        if (origin.equals(destination)) throw new SameOriginAndDestinationException();
        boolean newOriginNode =  !this.nodeNameTable.containsKey(origin);
        boolean newDestinationNode = !this.nodeNameTable.containsKey(destination);
        //Checks if a sink and source node with give identifiers are already in use
        if (!newDestinationNode && !newOriginNode) {
            //checks if section in opposing direction is found
            for (Edge e: this.adjArrayList.get(this.convertName(destination))) {
                if (e.getDestIdentifier().equals(origin)) {
                    throw new SectionInOpposingDirectionException();
                }
            }
            //tries to add new Section, but throws an Exception if it would make the escape network no longer valid
            Edge newEdge = new Edge(this.convertName(origin),
                    origin, this.convertName(destination), destination, capacity);
            this.tryToAddEdge(newEdge);
            this.capacityResultList.clear();
        }
        //TODO aufräumen
        if (newDestinationNode && !newOriginNode) {
            this.nodeNameTable.put(destination, this.nodes);
            this.nodes++;
            this.adjArrayList.add(new ArrayList<>());
            Edge newEdge = new Edge(this.convertName(origin),
                    origin, this.convertName(destination), destination, capacity);
            this.addEdge(newEdge);
            this.capacityResultList.clear();
        }
        if (!newDestinationNode && newOriginNode) {
            this.nodeNameTable.put(origin, this.nodes);
            this.nodes++;
            this.adjArrayList.add(new ArrayList<>());
            Edge newEdge = new Edge(this.convertName(origin),
                    origin, this.convertName(destination), destination, capacity);
            this.addEdge(newEdge);
            this.capacityResultList.clear();
        }
        if (newDestinationNode && newOriginNode) {
            this.nodeNameTable.put(origin, this.nodes);
            this.nodes++;
            this.adjArrayList.add(new ArrayList<>());
            this.nodeNameTable.put(destination, this.nodes);
            this.nodes++;
            this.adjArrayList.add(new ArrayList<>());
            Edge newEdge = new Edge(this.convertName(origin),
                    origin, this.convertName(destination), destination, capacity);
            this.addEdge(newEdge);
            this.capacityResultList.clear();
        }



    }

    /**
     * Looks up if result already calculated or needs to be calculated
     * returns capacity of escape network for given source and sink
     * @param sourceName identifier of source
     * @param sinkName identifier of sink
     * @return max capacity of escape network
     */
    public long computeCapacity(String sourceName, String sinkName) throws DatabaseException {
        for (CapacityResult r : this.capacityResultList) {
            if (r.source.identifier.equals(sourceName) && r.sink.identifier.equals(sinkName)) {
                return r.flowRate;
            }
        }
        if (!this.setSourceIndex(this.convertName(sourceName))) throw new NotAValidSourceException(sourceName);
        if (!this.setSinkIndex(this.convertName(sinkName))) throw new NotAValidSinkException(sinkName);
        long capacity = this.getCapacity();
        CapacityResult newCapacityResult = new CapacityResult(sourceName, sinkName, capacity);
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
        for (ArrayList<Edge> a : this.adjArrayList) {
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
    public ArrayList<CapacityResult> getResultList() {
        return this.capacityResultList;
    }

    private int convertName(final String name) throws IdentifierNotFoundException {
        if (this.nodeNameTable.get(name) == null) throw new IdentifierNotFoundException(name);
        return this.nodeNameTable.get(name);
    }

}