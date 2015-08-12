package com.exception;

import com.component.NamedVertex;

public class PathNotFoundException extends RuntimeException {
    private NamedVertex startNode;
    private NamedVertex endNode;

    public PathNotFoundException(NamedVertex startNode, NamedVertex endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }
}
