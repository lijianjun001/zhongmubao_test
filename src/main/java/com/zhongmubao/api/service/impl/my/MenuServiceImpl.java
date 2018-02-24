package com.zhongmubao.api.service.impl.my;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.Domain;
import com.zhongmubao.api.dao.CustomerHFDao;
import com.zhongmubao.api.dao.CustomerSinaDao;
import com.zhongmubao.api.dto.request.my.menu.ListRequestModel;
import com.zhongmubao.api.dto.response.my.menu.MenuItemModel;
import com.zhongmubao.api.dto.response.my.menu.ListViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.CustomerHF;
import com.zhongmubao.api.entity.CustomerSina;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.impl.BaseService;
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
    public ListViewModel list(Customer customer, ListRequestModel model) throws Exception {
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
        ListViewModel listViewModel = new ListViewModel();
        List<MenuItemModel> menuItemModels = new ArrayList<>();

        MenuItemModel menuItemModelRemindIndex = new MenuItemModel();
        menuItemModelRemindIndex.setIcon("personal-remind.png");
        menuItemModelRemindIndex.setTitle("购羊提醒");
        menuItemModelRemindIndex.setUrl("/Sheep/RemindIndex");
        menuItemModelRemindIndex.setAction("remindindex");
        menuItemModelRemindIndex.setJumpType("00");
        menuItemModels.add(menuItemModelRemindIndex);


        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        String dateStr = "2017-12-01 00:00:00";
        CustomerSina customerSina = customerSinaDao.getCustomerSinaById(customer.getId());
        CustomerHF customerHF = customerHFDao.getCustomerHFById(customer.getId());
        if (customer.getCreated().getTime() < (new SimpleDateFormat(dateFormat).parse(dateStr)).getTime()) {
            if (customerSina != null) {
                MenuItemModel menuItemModelWallet = new MenuItemModel();
                menuItemModelWallet.setIcon("personal-qianbao.png");
                menuItemModelWallet.setTitle("我的钱包");
                menuItemModelWallet.setUrl("/Emubao/WebApp#/Wallet");
                menuItemModelWallet.setAction("wallet");
                menuItemModelWallet.setJumpType("00");
                menuItemModels.add(menuItemModelWallet);
            }
        }
        if (customerHF != null) {
            if (customerHF.getIsBandCard() && customerHF.getIsBosAcct() && !StringUtil.isNullOrEmpty(customerHF.getUsrCustId())) {
                MenuItemModel menuItemModelHfWallet = new MenuItemModel();
                menuItemModelHfWallet.setIcon("personal-qianbao.png");
                menuItemModelHfWallet.setTitle("汇付钱包");
                menuItemModelHfWallet.setUrl("/Customer/HfWallet");
                menuItemModelHfWallet.setAction("hfwallet");
                menuItemModelHfWallet.setJumpType("00");
                menuItemModels.add(menuItemModelHfWallet);

                MenuItemModel menuItemModelHfCard = new MenuItemModel();
                menuItemModelHfCard.setIcon("personal-yinhangka.png");
                menuItemModelHfCard.setTitle("汇付银行卡");
                menuItemModelHfCard.setUrl("/Customer/HfCard");
                menuItemModelHfCard.setAction("hfcard");
                menuItemModelHfCard.setJumpType("00");
                menuItemModels.add(menuItemModelHfCard);
            }
        }


        MenuItemModel listMenuItemModelBonus = new MenuItemModel();
        listMenuItemModelBonus.setIcon("personal-xianjin.png");
        listMenuItemModelBonus.setTitle("现金红包");
        listMenuItemModelBonus.setUrl("/Customer/BonusList");
        listMenuItemModelBonus.setAction("bonuslist");
        listMenuItemModelBonus.setJumpType("00");
        menuItemModels.add(listMenuItemModelBonus);

        MenuItemModel listMenuItemModelOrder = new MenuItemModel();
        listMenuItemModelOrder.setIcon("personal-dingdan.png");
        listMenuItemModelOrder.setTitle("我的订单");
        listMenuItemModelOrder.setUrl("/Emubao/WebApp#/OrderList");
        listMenuItemModelOrder.setAction("orderlist");
        listMenuItemModelOrder.setJumpType("00");
        menuItemModels.add(listMenuItemModelOrder);

        MenuItemModel listMenuItemModelAddress = new MenuItemModel();
        listMenuItemModelAddress.setIcon("personal-address.png");
        listMenuItemModelAddress.setTitle("收货地址");
        listMenuItemModelAddress.setUrl("/Emubao/WebApp#/AddressList");
        listMenuItemModelAddress.setAction("addresslist");
        listMenuItemModelAddress.setJumpType("00");
        menuItemModels.add(listMenuItemModelAddress);

        MenuItemModel menuItemModelInvite = new MenuItemModel();
        menuItemModelInvite.setIcon("personal-referee.png");
        menuItemModelInvite.setTitle("我的推荐人");
        menuItemModelInvite.setUrl("/Emubao/WebApp#/Invite");
        menuItemModelInvite.setAction("invite");
        menuItemModelInvite.setJumpType("01");
        menuItemModels.add(menuItemModelInvite);

        MenuItemModel menuItemModelSettings = new MenuItemModel();
        menuItemModelSettings.setIcon("personal-set.png");
        menuItemModelSettings.setTitle("设置");
        menuItemModelSettings.setUrl("/Emubao/WebApp#/Settings");
        menuItemModelSettings.setAction("settings");
        menuItemModelSettings.setJumpType("01");
        menuItemModels.add(menuItemModelSettings);

        for (MenuItemModel menuItemModel :
                menuItemModels) {
            menuItemModel.setUrl(url + menuItemModel.getUrl());
            menuItemModel.setIcon(Constants.RESOURES_ADDRESS_IMAGES + menuItemModel.getIcon());
        }

        listViewModel.setList(menuItemModels);
        return listViewModel;
    }

}
