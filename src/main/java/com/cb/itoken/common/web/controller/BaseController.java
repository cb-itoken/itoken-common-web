package com.cb.itoken.common.web.controller;

import com.cb.itoken.common.domain.BaseDomain;
import com.cb.itoken.common.utils.MapperUtils;
import com.cb.itoken.common.web.components.datatables.DataTablesResult;
import com.cb.itoken.common.web.service.BaseClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:congbing
 * @Description 通用 Controller
 * @Date: 0:27 2019/1/28
 **/
public abstract class BaseController<T extends BaseDomain, S extends BaseClientService> {

    @Autowired
    protected S service;

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public DataTablesResult page(HttpServletRequest request){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        String json = service.page(start, length, null);
        DataTablesResult dataTablesResult = null;

        try {
            dataTablesResult = MapperUtils.json2pojo(json, DataTablesResult.class);
            dataTablesResult.setDraw(draw);
            dataTablesResult.setRecordsTotal(dataTablesResult.getCursor().getTotal());
            dataTablesResult.setRecordsFiltered(dataTablesResult.getCursor().getTotal());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataTablesResult;
    }
}
