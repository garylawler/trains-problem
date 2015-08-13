package com.exception;

public class PathNotFoundException extends RuntimeException {
    private Object startNode;
    private Object endNode;

    public PathNotFoundException(Object startNode, Object endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }
}
