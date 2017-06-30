package com.demo.proxy.dal.mapper;

import org.springframework.stereotype.Repository;

/**
 * @author: kevin.
 * @date: 2017/5/15.
 * @description:
 */
@Repository
public interface ShareBookMapper {


    int updateBooksAdminById(String cbooksId);

}
