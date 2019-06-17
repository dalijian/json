package com.lijian.concurrent.STM;

public final class VersionRef<T> {
    final T value;
    final long version;

    public VersionRef(T value, long version) {
        this.value = value;
        this.version = version;
    }

}

