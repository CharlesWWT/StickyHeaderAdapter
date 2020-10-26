# StickyHeaderAdapter
RecyclerView的头部悬浮 吸附悬浮 粘性布局 自定义悬浮头部布局
![image](https://github.com/CharlesWWT/StickyHeaderAdapter/blob/master/%E5%BD%95%E5%B1%8F.gif)
#使用方式
引入StickyHeaderDecoration和StickyHeaderAdapter两个类
原有的RecyclerView.Adapter 实现StickyHeaderAdapter接口 重写onBindHeaderViewHolder（header各项赋值）和onCreateHeaderViewHolder（指定header布局） getHeaderId（判断悬浮标题栏显示逻辑）三个方法即可 不侵入原有的RecyclerView.Adapter 