package com.softclub.util;

import lombok.Data;

/**
 * @Description: contains elements with timeout values.
 * @Author: Vasili Spirydzionak
 * @Date: 7/17/2020
 * @Copyright (c)
 */
public enum WaitTimeouts {
    /**
     * Maximum timeout for waiting assumed sec.
     */
    MAX(10),

    /**
     * Medium timeout for waiting assumed sec.
     */
    MIDDLE(5),

    /**
     * Minimum timeout for waiting assumed sec.
     */
    MIN(1);

    int timeouts;

    private WaitTimeouts(int timeouts) {
        this.timeouts = timeouts;
    }

    public int getTimeouts() {
        return timeouts;
    }
}
