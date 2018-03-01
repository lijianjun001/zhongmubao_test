package com.zhongmubao.api.util.datasource;

/**
 * @author 孙阿龙
 */
class HandleDataSource {
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    /**
     * 绑定当前线程数据源
     *
     * @param datasource 数据源
     */
    static void putDataSource(String datasource) {
        HOLDER.set(datasource);
    }

    /**
     * 获取当前线程的数据源
     *
     * @return HOLDER.get()
     */
    static String getDataSource() {
        return HOLDER.get();
    }
}
