package com.JasonSu.mutipleadapter.adapterItem;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * RecyclerView Item
 * Created by Jason Su on 2015/11/13.
 */
public class Item {

    private boolean ItemClickCallback;
    @NonNull
    private final BaseProvider mProvider;

    public Item(Item.Builder builder) {
        this.mProvider = builder.mProvider;
        this.ItemClickCallback = builder.ItemClickCallback;
    }

    public BaseProvider getProvider(){
        return mProvider;
    }

    public static class Builder {

        private Context mContext;
        private BaseProvider mProvider;
        private boolean ItemClickCallback;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setItemClickCallback(boolean itemClickCallback) {
            this.ItemClickCallback = itemClickCallback;
            return this;
        }

        public <T extends BaseProvider> T withProvider(T provider) {
            mProvider = provider;
            provider.setContext(mContext);
            provider.setBuilder(this);
            return provider;
        }

        @NonNull
        public Item build() {
            if (mProvider == null) {
                throw new IllegalStateException("You have to define the Item DataProvider");
            }
            return new Item(this);
        }
    }
}
