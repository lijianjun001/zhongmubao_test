package com.zhongmubao.api.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongmubao.api.cache.RedisCache;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.dao.ExtNoticeDao;
import com.zhongmubao.api.dao.SystemDistrictDao;
import com.zhongmubao.api.dto.Request.Address.SystemDistriceRequestModel;
import com.zhongmubao.api.dto.Request.PageNoticeRequestModel;
import com.zhongmubao.api.dto.Response.Address.ListSystemDistrictModel;
import com.zhongmubao.api.dto.Response.Address.SystemDistrictViewModel;
import com.zhongmubao.api.dto.Response.Notice.PageNoticeModel;
import com.zhongmubao.api.dto.Response.Notice.PageNoticeViewModel;
import com.zhongmubao.api.entity.ExtNotice;
import com.zhongmubao.api.entity.SystemDistrict;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.SystemService;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SystemServiceImpl implements SystemService {

//    private final ExtNoticeMongoDao extNoticeMongoDao;

    private final ExtNoticeDao extNoticeDao;
    private final SystemDistrictDao systemDistrictDao;
    private final RedisCache redisCache;

    public SystemServiceImpl(ExtNoticeDao extNoticeDao, SystemDistrictDao systemDistrictDao, RedisCache redisCache) {
//        this.extNoticeMongoDao = extNoticeMongoDao;
        this.extNoticeDao = extNoticeDao;
        this.systemDistrictDao = systemDistrictDao;
        this.redisCache = redisCache;
    }

    //region 系统通知
    @Override
    public PageNoticeModel pageNotice(PageNoticeRequestModel requestModel) throws Exception {
        if (null == requestModel) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        PageHelper.startPage(requestModel.getPageIndex(), Constants.PAGE_SIZE);
        Page<ExtNotice> page = extNoticeDao.pageEffectiveExtNotice();
        int pages = page.getPages();
        List<PageNoticeViewModel> list = page.getResult().stream().map(
                en -> new PageNoticeViewModel(
                        en.getId(),
                        en.getPic(),
                        en.getTitle(),
                        en.getContent(),
                        DateUtil.formart(en.getCreated(), "yyyy-MM-dd")
                ))
                .collect(Collectors.toList());
        PageHelper.clearPage();
//        int totalCount = requestModel.getPageIndex() <= 1 ? extNoticeMongoDao.countByCustomerIdAndType() : 0;
        return new PageNoticeModel(pages, list);
    }
    //endregion

    //region 系统省市区

    /**
     * 获取省市区By ParentCode
     *
     * @param requestModel 取ParentCode
     * @return SystemDistrict列表
     * @throws Exception
     */
    @Override
    public ListSystemDistrictModel GetSystemDistrictByParentCode(SystemDistriceRequestModel requestModel) throws Exception {
        if (null == requestModel) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        List<SystemDistrict> systemDistrictList = redisCache.getDistrictList();
        if (null == systemDistrictList) {
            List<SystemDistrict> districts = systemDistrictDao.pagerSystemDistrictList(0, 4000);
            redisCache.savaDistrictList(districts);
            systemDistrictList = districts;
        }

        // 筛选过滤
        Predicate<SystemDistrict> parentCodeFilter = null;
        String parentCode = requestModel.getParentCode();
        if (StringUtil.isNullOrEmpty(parentCode)) {
            parentCodeFilter = (code) -> (code.getParentCode() == null);    // 取所有省
        } else {
            parentCodeFilter = (code) -> (code.getParentCode() != null && code.getParentCode().equals(parentCode));
        }

        List<SystemDistrictViewModel> list = systemDistrictList.stream()
                .filter(parentCodeFilter).map(
                        en -> new SystemDistrictViewModel(
                                en.getCode(),
                                en.getParentCode(),
                                en.getName()
                        ))
                .collect(Collectors.toList());

        return new ListSystemDistrictModel(list);
    }

    //endregion

}
