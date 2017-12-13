package com.zhongmubao.api.dto.response.my.transaction;

import com.zhongmubao.api.config.enmu.TransactionDetailType;

/**
 * 交易详情记录视图实体
 *
 * @author 米立林
 */
public class TransactionDetailViewModel {
    public TransactionDetailViewModel() {
    }

    public TransactionDetailViewModel(TransactionDetailType billType, WithdrawDetailModel withdrawDetailModel, BuySheepDetailModel buySheepDetailModel, RechargeDetailModel rechargeDetailModel) {
        this.billType = billType;
        this.withdrawDetailModel = withdrawDetailModel;
        this.buySheepDetailModel = buySheepDetailModel;
        this.rechargeDetailModel = rechargeDetailModel;
    }

    /**
     * 账单类型（充值、提现、买羊、赎回）
     */
    private TransactionDetailType billType;

    private WithdrawDetailModel withdrawDetailModel;

    private BuySheepDetailModel buySheepDetailModel;

    private RechargeDetailModel rechargeDetailModel;

    public TransactionDetailType getBillType() {
        return billType;
    }

    public void setBillType(TransactionDetailType billType) {
        this.billType = billType;
    }

    public WithdrawDetailModel getWithdrawDetailModel() {
        return withdrawDetailModel;
    }

    public void setWithdrawDetailModel(WithdrawDetailModel withdrawDetailModel) {
        this.withdrawDetailModel = withdrawDetailModel;
    }

    public BuySheepDetailModel getBuySheepDetailModel() {
        return buySheepDetailModel;
    }

    public void setBuySheepDetailModel(BuySheepDetailModel buySheepDetailModel) {
        this.buySheepDetailModel = buySheepDetailModel;
    }

    public RechargeDetailModel getRechargeDetailModel() {
        return rechargeDetailModel;
    }

    public void setRechargeDetailModel(RechargeDetailModel rechargeDetailModel) {
        this.rechargeDetailModel = rechargeDetailModel;
    }
}
