package de.reach.api.controller;

import de.reach.api.resources.BlackListResponse;
import de.reach.api.resources.BlackListModificationResponse;
import de.reach.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/v1/blacklist")
public class BlackListController {


    @Autowired
    private BlackListService blackListService;

    @RequestMapping(value = "/{ip}",method = RequestMethod.POST)
    public BlackListModificationResponse addIp(@PathVariable("ip") String ip) {

        return blackListService.addIp(ip);
    }

    @RequestMapping(value = "/{ip}",method = RequestMethod.GET)
    public BlackListModificationResponse getIp(@PathVariable("ip") String ip) {

        return blackListService.checkIpIsExist(ip);
    }

    @RequestMapping(value = "/{ip}",method = RequestMethod.DELETE)
    public BlackListModificationResponse deleteIp(@PathVariable("ip") String ip) {

        return blackListService.deleteIp(ip);
    }

    @RequestMapping(method = RequestMethod.GET)
    public BlackListResponse getBlackList() {

        return blackListService.getList();
    }


}
