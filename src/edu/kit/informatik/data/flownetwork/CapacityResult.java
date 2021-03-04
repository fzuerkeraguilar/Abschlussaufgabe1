package edu.kit.informatik.data.flownetwork;

/**
 * Class that stores result of a capacity calculation
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class CapacityResult implements Comparable<CapacityResult> {
    //TODO private und getter für die identifier?
    /**
     * Node that was used as the source of the flow during the calculation
     */
    protected final Node source;
    /**
     * Node that was used as the source of the flow during the calculation
     */
    protected final Node sink;
    /**
     * flow rate that was calculated
     */
    protected final long flowRate;

    /**
     * constructor of a calculated flow result
     * @param source identifier of the node that the flow originated from
     * @param sink identifier of the node that the flow flowed to
     * @param flowRate calculated flow rate for given source and sink
     */
    public CapacityResult(String source, String sink, long flowRate) {
        this.source = new Node(source);
        this.sink = new Node(sink);
        this.flowRate = flowRate;
    }

    /**
     * Comparator to other result for sorting
     * @param r other given result
     * @return 1 if this
     */
    @Override
    public int compareTo(CapacityResult r) {
        if (this.flowRate == r.flowRate) {
            if (this.source.identifier.equals(r.source.identifier)) {
                return this.sink.identifier.compareTo(r.sink.identifier);
            } else {
                return this.source.identifier.compareTo(r.source.identifier);
            }
        } else {
            return Long.compare(this.flowRate, r.flowRate);
        }
    }

    /**
     * Own implementation of toString method
     * @return string representation of this result in this format: <v_1><k><v_2>
     */
    @Override
    public String toString() {
        String output = this.flowRate + " ";
        output += this.source.identifier + " ";
        output += this.sink.identifier;
        return output;
    }
}
