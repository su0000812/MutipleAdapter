package com.JasonSu.mutipleadapter.adapterItem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Jason Su on 2015/10/30.
 */
public class Item {

    private final BaseProvider mProvider;
    private Object mTag;
    private boolean mItemClickable;

    /**
     * Creates a new Item.
     *
     * @param
     */
    private Item(final Builder builder) {
        mProvider = builder.mProvider;
        mTag = builder.mTag;
        mItemClickable = builder.mItemClickable;
    }

    public Object getmTag() {
        return mTag;
    }

    public boolean isItemClickable() {
        return mItemClickable;
    }

    public void setItemClickable(boolean mItemClickable) {
        this.mItemClickable = mItemClickable;
    }

    /**
     * Get the Item content.
     *
     * @return
     */
    @NonNull
    public BaseProvider getProvider() {
        return mProvider;
    }

    public static class Builder {
        @NonNull
        private final Context mContext;
        @Nullable
        private Object mTag;
        private boolean mDismissible;
        private BaseProvider mProvider;
        private boolean mItemClickable;

        public Builder setItemClickable(boolean isItemClickable) {
            this.mItemClickable = isItemClickable;
            return this;
        }

        public Builder setTag(Object tag) {
            this.mTag = tag;
            return this;
        }

        /**
         * Creates a new Builder.
         *
         * @param
         */
        public Builder(final Context context) {
            mContext = context;
        }

        /**
         * Set the provider.
         *
         * @param
         * @param
         * @return
         */
        @NonNull
        public <T extends BaseProvider> T withProvider(T content) {
            mProvider = content;
            content.setContext(mContext);
            content.setBuilder(this);
            return content;
        }

        /**
         * Builds the item.
         *
         * @return
         */
        @NonNull
        public Item build() {
            if (mProvider == null) {
                throw new IllegalStateException("You have to define the Item DataProvider");
            }
            return new Item(this);
        }
    }
}
