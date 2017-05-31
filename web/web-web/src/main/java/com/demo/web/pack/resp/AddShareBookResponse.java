package com.demo.web.pack.resp;

import com.demo.web.bean.ShareBookBean;
import com.caiyi.accountbookweb.bean.ShareFriendsMarkBean;
import com.caiyi.accountbookweb.bean.ShareMemberBean;
import com.demo.web.pack.base.BaseResponse;

import java.util.List;

/**
 * @author: kevin.
 * @date: 2017/5/26.
 * @description:
 */
public class AddShareBookResponse extends BaseResponse {

    private ShareBookBean shareBook;

    private List<ShareMemberBean> shareMembers;

    private List<ShareFriendsMarkBean> shareFriendsMarks;

    public ShareBookBean getShareBook() {
        return shareBook;
    }

    public void setShareBook(ShareBookBean shareBook) {
        this.shareBook = shareBook;
    }

    public List<ShareMemberBean> getShareMembers() {
        return shareMembers;
    }

    public void setShareMembers(List<ShareMemberBean> shareMembers) {
        this.shareMembers = shareMembers;
    }

    public List<ShareFriendsMarkBean> getShareFriendsMarks() {
        return shareFriendsMarks;
    }

    public void setShareFriendsMarks(List<ShareFriendsMarkBean> shareFriendsMarks) {
        this.shareFriendsMarks = shareFriendsMarks;
    }
}
