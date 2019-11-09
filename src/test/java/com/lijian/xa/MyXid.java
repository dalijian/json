package com.lijian.xa;

import javax.transaction.xa.Xid;

public class MyXid implements Xid {

    private int formatId;
    private byte[] gtrid;
    private byte[] bqual;

    public MyXid(int i, byte[] bytes, byte[] bytes1) {
        this.formatId=i;
        this.gtrid=bytes;
        this.bqual=bytes1;
    }

    @Override
    public int getFormatId() {

        return formatId;
    }

    @Override
    public byte[] getGlobalTransactionId() {
        return gtrid;
    }

    @Override
    public byte[] getBranchQualifier() {
        return bqual;
    }
}
