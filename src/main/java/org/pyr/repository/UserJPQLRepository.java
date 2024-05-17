package org.pyr.repository;

import org.pyr.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserJPQLRepository extends PagingAndSortingRepository<User, Long> {

    @Query("From User where custName = :custName")
    User findUserByCustName(@Param("custName") String custName);

    @Query("From User where custName = ?1")
    User findUserByCustName2(String custName);

    @Transactional // 通常在业务逻辑中service增加，而不是这里
    @Modifying
    @Query("UPDATE User set custName = :custName where custId = :custId")
    int updateCutomserNameById(@Param("custName") String custName, @Param("custId") long custId);

    @Transactional // 通常在业务逻辑中service增加，而不是这里
    @Modifying
    @Query("DELETE from User where custId = :custId")
    int deleteCutomserById(@Param("custId") long custId);

    @Query(value = "select * from cst_User where cust_name = :custName", nativeQuery = true)
    User findUserByCustNameWithNative(@Param("custName") String custName);
}
