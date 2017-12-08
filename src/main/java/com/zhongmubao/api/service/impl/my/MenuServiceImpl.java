package com.zhongmubao.api.service.impl.my;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.Domain;
import com.zhongmubao.api.config.enmu.RealNameStatus;
import com.zhongmubao.api.dao.CustomerHFDao;
import com.zhongmubao.api.dao.CustomerSinaDao;
import com.zhongmubao.api.dto.request.my.center.PersonalCenterRequestModel;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.response.my.center.PersonalCenterViewModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.dto.response.my.center.PersonalCenterItemModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.CustomerHF;
import com.zhongmubao.api.entity.CustomerSina;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.BaseService;
import com.zhongmubao.api.service.my.MenuService;
import com.zhongmubao.api.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 个人中心
 *
 * @author xy
 */
@Service
public class MenuServiceImpl extends BaseService implements MenuService {
    private final CustomerHFDao customerHFDao;
    private final CustomerSinaDao customerSinaDao;

    @Autowired
    public MenuServiceImpl(CustomerHFDao customerHFDao, CustomerSinaDao customerSinaDao) {
        this.customerHFDao = customerHFDao;
        this.customerSinaDao = customerSinaDao;
    }

    @Override
    public PersonalCenterViewModel personalCenter(Customer customer, PersonalCenterRequestModel model) throws Exception {
        String url = "";
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getPlatform() != null) {
            if (model.getPlatform().equals(Domain.IOS.getPlatform())) {
                url = Domain.IOS.getDomain();
            }
            if (model.getPlatform().equals(Domain.ANDROID.getPlatform())) {
                url = Domain.ANDROID.getDomain();
            }
        }
        PersonalCenterViewModel personalCenterViewModel = new PersonalCenterViewModel();
        List<PersonalCenterItemModel> personalCenterItemModels = new ArrayList<>();

        PersonalCenterItemModel personalCenterItemModelRemindIndex = new PersonalCenterItemModel();
        personalCenterItemModelRemindIndex.setIcon("personal-remind.png");
        personalCenterItemModelRemindIndex.setTitle("购羊提醒");
        personalCenterItemModelRemindIndex.setUrl("/Sheep/RemindIndex");
        personalCenterItemModelRemindIndex.setAction("remindindex");
        personalCenterItemModelRemindIndex.setJumpType("00");
        personalCenterItemModels.add(personalCenterItemModelRemindIndex);

        PersonalCenterItemModel personalCenterItemModelWallet = new PersonalCenterItemModel();
        personalCenterItemModelWallet.setIcon("personal-qianbao.png");
        personalCenterItemModelWallet.setTitle("我的钱包");
        personalCenterItemModelWallet.setUrl("/Emubao/WebApp#/Wallet");
        personalCenterItemModelWallet.setAction("wallet");
        personalCenterItemModelWallet.setJumpType("00");
        personalCenterItemModels.add(personalCenterItemModelWallet);

        PersonalCenterItemModel personalCenterItemModelHfWallet = new PersonalCenterItemModel();
        personalCenterItemModelHfWallet.setIcon("personal-qianbao.png");
        personalCenterItemModelHfWallet.setTitle("汇付钱包");
        personalCenterItemModelHfWallet.setUrl("/Customer/HfWallet");
        personalCenterItemModelHfWallet.setAction("hfwallet");
        personalCenterItemModelHfWallet.setJumpType("00");
        personalCenterItemModels.add(personalCenterItemModelHfWallet);

        PersonalCenterItemModel personalCenterItemModelHfCard = new PersonalCenterItemModel();
        personalCenterItemModelHfCard.setIcon("personal-yinhangka.png");
        personalCenterItemModelHfCard.setTitle("汇付银行卡");
        personalCenterItemModelHfCard.setUrl("/Customer/HfCard");
        personalCenterItemModelHfCard.setAction("hfcard");
        personalCenterItemModelHfCard.setJumpType("00");
        personalCenterItemModels.add(personalCenterItemModelHfCard);

        PersonalCenterItemModel personalCenterItemModelBonusList = new PersonalCenterItemModel();
        personalCenterItemModelBonusList.setIcon("personal-xianjin.png");
        personalCenterItemModelBonusList.setTitle("现金红包");
        personalCenterItemModelBonusList.setUrl("/Customer/BonusList");
        personalCenterItemModelBonusList.setAction("bonuslist");
        personalCenterItemModelBonusList.setJumpType("00");
        personalCenterItemModels.add(personalCenterItemModelBonusList);

        PersonalCenterItemModel personalCenterItemModelOrderList = new PersonalCenterItemModel();
        personalCenterItemModelOrderList.setIcon("personal-dingdan.png");
        personalCenterItemModelOrderList.setTitle("我的订单");
        personalCenterItemModelOrderList.setUrl("/Emubao/WebApp#/OrderList");
        personalCenterItemModelOrderList.setAction("orderlist");
        personalCenterItemModelOrderList.setJumpType("00");
        personalCenterItemModels.add(personalCenterItemModelOrderList);

        PersonalCenterItemModel personalCenterItemModelAddressList = new PersonalCenterItemModel();
        personalCenterItemModelAddressList.setIcon("personal-address.png");
        personalCenterItemModelAddressList.setTitle("收货地址");
        personalCenterItemModelAddressList.setUrl("/Emubao/WebApp#/AddressList");
        personalCenterItemModelAddressList.setAction("addresslist");
        personalCenterItemModelAddressList.setJumpType("00");
        personalCenterItemModels.add(personalCenterItemModelAddressList);

        PersonalCenterItemModel personalCenterItemModelInvite = new PersonalCenterItemModel();
        personalCenterItemModelInvite.setIcon("personal-referee.png");
        personalCenterItemModelInvite.setTitle("我的推荐人");
        personalCenterItemModelInvite.setUrl("/Emubao/WebApp#/Invite");
        personalCenterItemModelInvite.setAction("invite");
        personalCenterItemModelInvite.setJumpType("01");
        personalCenterItemModels.add(personalCenterItemModelInvite);

        PersonalCenterItemModel personalCenterItemModelSettings = new PersonalCenterItemModel();
        personalCenterItemModelSettings.setIcon("personal-set.png");
        personalCenterItemModelSettings.setTitle("设置");
        personalCenterItemModelSettings.setUrl("/Emubao/WebApp#/Settings");
        personalCenterItemModelSettings.setAction("settings");
        personalCenterItemModelSettings.setJumpType("01");
        personalCenterItemModels.add(personalCenterItemModelSettings);

        for (PersonalCenterItemModel itemModel :
                personalCenterItemModels) {
            itemModel.setUrl(url + itemModel.getUrl());
            itemModel.setIcon(Constants.RESOURES_ADDRESS_IMAGES + itemModel.getIcon());
        }

        personalCenterViewModel.setList(personalCenterItemModels);
        return personalCenterViewModel;
    }

    @Override
    public RealNameViewModel realName(Customer customer, RealNameRequestModel model) throws Exception {

        RealNameViewModel realNameViewModel = new RealNameViewModel();
        CustomerHF customerHF = customerHFDao.getCustomerHFById(customer.getId());

        //Redis 获取显示新浪 还是 汇付
        boolean ishf = false;
        String dateFormat ="yyyy-MM-dd HH:mm:ss";
        String dateStr = "2017-12-01 00:00:00";
        if (customer.getCreated().getTime() > (new SimpleDateFormat(dateFormat).parse(dateStr)).getTime()) {
            ishf = true;
        }
        if (!ishf && customerHF != null) {
            ishf = true;
        }

        //显示汇付 or 新浪
        if (ishf) {
            if (customerHF == null) {
                realNameViewModel.setRealName(RealNameStatus.HFF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFF.getImg());
                return realNameViewModel;
            }
            if (!StringUtil.isNullOrEmpty(customerHF.getUsrCustId()) && customerHF.getIsBandCard() && customerHF.getIsBosAcct()) {
                realNameViewModel.setRealName(RealNameStatus.HFS.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFS.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFS.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFS.getImg());
            } else if (!StringUtil.isNullOrEmpty(customerHF.getUsrCustId()) && !customerHF.getIsBosAcct()) {
                realNameViewModel.setRealName(RealNameStatus.HFB.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFB.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFB.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFB.getImg());
            } else {
                realNameViewModel.setRealName(RealNameStatus.HFF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFF.getImg());
            }
        } else {
            CustomerSina customerSina = customerSinaDao.getCustomerSinaById(customer.getId());
            if (customerSina == null) {
                realNameViewModel.setRealName(RealNameStatus.XLF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLF.getImg());
                return realNameViewModel;
            }

            if (customerSina.getIsRealName()) {
                realNameViewModel.setRealName(RealNameStatus.XLS.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLS.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLS.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLS.getImg());
            } else {
                realNameViewModel.setRealName(RealNameStatus.XLF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLF.getImg());
            }

        }
        return realNameViewModel;
    }
}
