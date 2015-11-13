package com.JasonSu.mutipleadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JasonSu.mutipleadapter.adapterItem.Item;
import com.JasonSu.mutipleadapter.adapterItem.ItemLayout;

/**
 * 适用于滑动布局中最后为列表
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

    private Item[] itemList;

    public BaseAdapter(Item... items) {
        this.itemList = items;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (position > itemList.length - 1) {
            holder.build(itemList[itemList.length - 1], position - (itemList.length - 1));
        }
        if (position == itemList.length - 1) {
            holder.build(itemList[position], position - (itemList.length - 1));
        }
        if (position < itemList.length - 1) {
            holder.build(itemList[position], position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position > itemList.length - 1) {
            return itemList[itemList.length - 1].getProvider().getLayoutResId();
        }
        return itemList[position].getProvider().getLayoutResId();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (int i = 0; i < itemList.length; i++) {
            if (itemList[i].getProvider().getDataList() != null && itemList[i].getProvider().getDataList().size() > 0) {
                count += itemList[i].getProvider().getDataList().size();
            } else {
                count += 1;
            }
        }
        return count;
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        private ItemLayout view;

        public BaseViewHolder(View view) {
            super(view);
            this.view = (ItemLayout) view;
        }

        public void build(Item item, int position) {
            item.getProvider().render(view, item, position);
        }
    }
}
