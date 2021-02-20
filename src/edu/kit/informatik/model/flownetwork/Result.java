package edu.kit.informatik.model.flownetwork;

public class Result implements Comparable<Result> {
    Node source;
    Node sink;
    long flowRate;

    public Result(String source, String sink, long flowRate){
        this.source = new Node(source);
        this.sink = new Node(sink);
        this.flowRate = flowRate;
    }


    @Override
    public int compareTo(Result r) {
        if(this.flowRate == r.flowRate){
            if(this.source.identifier.equals(r.source.identifier)){
                return this.sink.identifier.compareTo(r.sink.identifier);
            } else {
                return this.source.identifier.compareTo(r.source.identifier);
            }
        } else {
            return Long.compare(this.flowRate, r.flowRate);
        }
    }

    @Override
    public String toString(){
        String output = this.flowRate + " ";
        output += this.source.identifier + " ";
        output += this.sink.identifier;
        return output;
    }
}
