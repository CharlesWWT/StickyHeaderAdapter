package com.test.kutesmart.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/26.
 */

public class PhoneContactAdapter extends RecyclerView.Adapter<PhoneContactAdapter.ViewHolder> implements StickyHeaderAdapter<PhoneContactAdapter.HeaderHolder> {


    private ArrayList<ContactsBean> contactLists;
    private Context context;
    private final LayoutInflater mInflater;
//    private char lastChar = '\u0000';
    private String lastChar="";
    private int DisplayIndex = 0;
    //私有属性
    private OnItemClickListener onItemClickListener = null;


    //回调接口
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


    public PhoneContactAdapter(Context context, ArrayList<ContactsBean> contactLists) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.contactLists = contactLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mInflater.inflate(R.layout.item_phone_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ContactsBean contactsBean = contactLists.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
        holder.nickName.setText(contactsBean.getName());
        try {
            holder.tv_xing.setText(contactsBean.getName().charAt(0) + "");
        } catch (Exception e) {
            holder.tv_xing.setText("#");
        }


        if (position == 0) {
            holder.diviView.setVisibility(View.INVISIBLE);
        } else {
            ContactsBean currentItem = contactLists.get(position);
            ContactsBean lastItem = contactLists.get(position - 1);
            if (!currentItem.getPinyinFirst().equals(lastItem.getPinyinFirst())) {
                holder.diviView.setVisibility(View.INVISIBLE);
            } else {
                holder.diviView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return contactLists.size();
    }

    //=================悬浮栏=================
    @Override
    public long getHeaderId(int position) {
        //这里面的是如果当前position与之前position重复（内部判断）  则不显示悬浮标题栏  如果不一样则显示标题栏
        if (null != contactLists.get(position) && contactLists.get(position).getPinyinFirst().charAt(0) != '\0') {
//            char ch = contactLists.get(position).getPinyinFirst().charAt(0);
            String ch = contactLists.get(position).getPinyinFirst();
            if (lastChar == "") {
                lastChar = ch;
                return DisplayIndex;
            } else {
                if (lastChar == ch) {
                    return DisplayIndex;
                } else {
                    lastChar = ch;
                    DisplayIndex++;
                    return DisplayIndex;
                }

            }
        } else {
            return DisplayIndex;
        }
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.item_contacts_head, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        if (contactLists.get(position).getPinyinFirst()== "") {
            viewholder.header.setText("#");
        } else {
            viewholder.header.setText(contactLists.get(position).getPinyinFirst() + "");
        }
    }

    public int getPositionForSection(char pinyinFirst) {
        for (int i = 0; i < getItemCount(); i++) {
            char firstChar = contactLists.get(i).getPinyinFirst().charAt(0);
            if (firstChar == pinyinFirst) {
                return i;
            }
        }
        return -1;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nickName;
        private final TextView tvCompany;
        private final TextView tvJob;
        private final View diviView;
        private final TextView tv_xing;

        public ViewHolder(View itemView) {
            super(itemView);
            nickName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCompany = (TextView) itemView.findViewById(R.id.tv_company);
            tvJob = (TextView) itemView.findViewById(R.id.tv_job);
            tv_xing = (TextView) itemView.findViewById(R.id.tv_xing);
            diviView = itemView.findViewById(R.id.vw_divisition);
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView;
        }
    }
}
