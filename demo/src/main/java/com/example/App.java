package com.example;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class App {
    public static void main(String[] args) {
        int dModel = 64;
        int numHeads = 8;
        int seqLength = 5;

        MultiHeadAttention mha = new MultiHeadAttention(numHeads, dModel);

        INDArray q = Nd4j.rand(seqLength, dModel);
        INDArray k = Nd4j.rand(seqLength, dModel);
        INDArray v = Nd4j.rand(seqLength, dModel);

        INDArray output = mha.apply(q, k, v);
        System.out.println("Multi-head attention output shape: " + output.shapeInfoToString());
    }
}
