package com.demo.web.document;

import com.demo.web.bean.ShareBookBean;
import com.caiyi.accountbookweb.bean.ShareFriendsMarkBean;
import com.caiyi.accountbookweb.bean.ShareMemberBean;

import java.util.List;

/**
 * @author: kevin.
 * @date: 2017/5/24.
 * @description:
 */
public class ShareBookDocResponse extends DocBaseResponse {


    private ShareBookDocResult results;

    public ShareBookDocResult getResults() {
        return results;
    }

    public void setResults(ShareBookDocResult results) {
        this.results = results;
    }

    private class ShareBookDocResult{
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
}
