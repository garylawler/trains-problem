package com.exception;

public class PathNotFoundException extends RuntimeException {
    private Object startVertex;
    private Object endVertex;

    public PathNotFoundException(Object startVertex, Object endVertex) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    }

    public Object getStartVertex() {
        return startVertex;
    }

    public Object getEndVertex() {
        return endVertex;
    }
}
