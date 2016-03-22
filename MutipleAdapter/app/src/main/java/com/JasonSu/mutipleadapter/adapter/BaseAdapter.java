package com.JasonSu.mutipleadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JasonSu.mutipleadapter.adapterItem.Item;
import com.JasonSu.mutipleadapter.adapterItem.ItemLayout;

import java.util.List;

/**
 * Created by Jason Su on 2015/10/30.
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

    private List<Item> mList;
    private onItemClickListener mListener;

    public BaseAdapter(List<Item> list) {
        this.mList = list;
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public interface onItemClickListener {
        public abstract void itemClick(int position, Item item);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        if (mList.get(position).isItemClickable()) {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        try {
                            mListener.itemClick(position, mList.get(position));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        holder.build(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getProvider().getLayout();
    }

    public void insert(Item item) {
        int position = getItemCount();
        mList.add(item);
        this.notifyItemInserted(position);
    }

    public void insert(List<Item> list) {
        int position = this.getItemCount();
        mList.addAll(list);
        this.notifyItemRangeInserted(position, list.size());
    }

    public void replace(List<Item> list) {
        if (mList.size() < list.size()) {
            this.notifyItemRangeInserted(mList.size() - 1, list.size() - mList.size());
        }
        if (mList.size() > list.size()) {
            this.notifyItemRangeRemoved(list.size() - 1, mList.size() - list.size());
        }
        this.mList.clear();
        this.notifyItemRangeRemoved(0, list.size());
        this.mList.addAll(list);
        this.notifyItemRangeInserted(0, list.size());
    }

    public void update(List<Item> list) {
        if (mList.size() < list.size()) {
            this.notifyItemRangeInserted(mList.size() - 1, list.size() - mList.size());
        }
        if (mList.size() > list.size()) {
            this.notifyItemRangeRemoved(list.size() - 1, mList.size() - list.size());
        }
        this.mList = list;
        this.notifyDataSetChanged();
    }

    public void remove(int position) {
        this.mList.remove(mList.get(position));
        this.notifyItemRemoved(position);
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        private ItemLayout view;

        public BaseViewHolder(View itemView) {
            super(itemView);
            this.view = (ItemLayout) itemView;
        }

        public void build(Item item, int position) {
            view.build(item, position);
        }

    }
}
