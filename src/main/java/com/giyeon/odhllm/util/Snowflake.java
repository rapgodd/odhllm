package com.giyeon.odhllm.util;


public class Snowflake {
    private static final int UNUSED_SIGH_BITS = 1;
    private static final int EPOCH_BITS = 41;
    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;

    private final long nodeId;
    private final long customEpoch;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    public Snowflake(long nodeId, long customEpoch) {
        this.nodeId = nodeId;
        this.customEpoch = customEpoch;
    }

    public synchronized long nextId() {

        long currentTimestamp = System.currentTimeMillis() - customEpoch;

        if (currentTimestamp == lastTimestamp){
            sequence = sequence + 1;
        }else{
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;

        return currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS)
                | (nodeId << SEQUENCE_BITS)
                | sequence;
    }




}
