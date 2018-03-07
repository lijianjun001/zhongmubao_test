package com.zhongmubao.api.util;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.enmu.Domain;
import com.zhongmubao.api.config.enmu.Platform;
import com.zhongmubao.api.config.enmu.SheepProjectPeriod;

/**
 * 接口基础类
 *
 * @author 孙阿龙
 */
public class ApiUtil {
    /**
     * 计算羊只利率
     *
     * @param price  羊只单价
     * @param rate   比率
     * @param period 周期
     * @return 收益
     * @author 米立林
     */
    public static double calcProfitEx(double price, double rate, int period) {
        if (period == SheepProjectPeriod.PERIOD_120.getName() && rate == 13.50) {
            return Math.ceil(price * rate / 100 * period / 365);
        } else {
            return (price * rate / 100 * period / 365);
        }
    }

    /**
     * 通过平台获取平台域名
     *
     * @param platform 平台
     * @return
     */
    public static String getDomainByPlatform(String platform) {
        if (Platform.IOS.getName().equals(platform)) {
            return Domain.IOS.getDomain();
        } else if (Platform.ANDROID.getName().equals(platform)) {
            return Domain.ANDROID.getDomain();
        } else if (Platform.WEIXIN.getName().equals(platform)) {
            return Domain.WEIXIN.getDomain();
        } else if (Platform.WAP.getName().equals(platform)) {
            return Domain.WAP.getDomain();
        }
        return "";
    }


    /**
     * 生成Code
     *
     * @param customerId 客户id
     * @return code
     * @author 米立林
     */
    public static String inviteCode(int customerId) {
        int maxLength = 6;
        String str = Integer.toString(customerId + 88888);

        int length = maxLength - str.length();
        if (length <= 0) {
            return str;
        }
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j <= maxLength - str.length(); j++) {
            buffer.append("Y" + str);
        }
        str = buffer.toString();
        return str;
    }

    /**
     * code解码
     *
     * @param code 客户code
     * @return 客户id
     * @author 米立林
     */
    public static int dInviteCode(String code) {
        try {
            String str = code.trim().toLowerCase().replace("y", Constants.EMPTY_STRING);
            return Integer.parseInt(str) - 88888;
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 格式化用户头像
     *
     * @param photo
     * @return
     */
    public static String formartPhoto(String photo) {
        return StringUtil.isNullOrEmpty(photo) ? Constants.DEFAULT_PHOTO : photo.toLowerCase().startsWith(Constants.HTTP) ? photo : Domain.RESOURCES.getDomain() + photo;
    }
}
