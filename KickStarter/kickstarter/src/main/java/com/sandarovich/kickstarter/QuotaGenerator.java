package com.sandarovich.kickstarter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Quota Generator.
 */

public class QuotaGenerator {
    private static final List<String> QUOATES;

    static {
        QUOATES = new ArrayList<String>();
        QUOATES.add("\"Every big journey begins with a small step\"");
        QUOATES.add("\"No pain, no gain\"");
        QUOATES.add("\"Excellence across the board \"");
    }

    String getQuota(int quotaIndex) {
        return (quotaIndex <= QUOATES.size()) ? QUOATES.get(quotaIndex) : QUOATES.get(0);
    }

    public String getQuota() {
        int quotaIndex = new Random().nextInt(QUOATES.size());
        return getQuota(quotaIndex);
    }
}