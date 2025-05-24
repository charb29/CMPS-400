package com.example;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

public class MultiHeadAttention {
    private int numHeads;
    private int dModel;
    private int depth;
    private INDArray[] wq, wk, wv, wo;

    public MultiHeadAttention(int numHeads, int dModel) {
        this.numHeads = numHeads;
        this.dModel = dModel;
        this.depth = dModel / numHeads;

        wq = new INDArray[numHeads];
        wk = new INDArray[numHeads];
        wv = new INDArray[numHeads];
        wo = new INDArray[numHeads];

        for (int i = 0; i < numHeads; i++) {
            wq[i] = Nd4j.rand(dModel, depth);
            wk[i] = Nd4j.rand(dModel, depth);
            wv[i] = Nd4j.rand(dModel, depth);
            wo[i] = Nd4j.rand(depth, dModel);
        }
    }

    private INDArray scaledDotProductAttention(INDArray q, INDArray k, INDArray v) {
        INDArray matmulQK = q.mmul(k.transpose());
        INDArray scaled = matmulQK.div(Math.sqrt(depth));
        INDArray softMax = Transforms.softmax(scaled, true);
        return softMax.mmul(v);
    }

    public INDArray apply(INDArray qInput, INDArray kInput, INDArray vInput) {
        INDArray[] attentionHeads = new INDArray[numHeads];

        for (int i = 0; i < numHeads; i++) {
            INDArray q = qInput.mmul(wq[i]);
            INDArray k = kInput.mmul(wk[i]);
            INDArray v = vInput.mmul(wv[i]);

            attentionHeads[i] = scaledDotProductAttention(q, k, v);
        }

        INDArray concatenated = Nd4j.hstack(attentionHeads);
        INDArray output = Nd4j.zeros(concatenated.shape()[0], dModel);
        for (int i = 0; i < numHeads; i++) {
            output.addi(attentionHeads[i].mmul(wo[i]));
        }

        return output;
    }
}
