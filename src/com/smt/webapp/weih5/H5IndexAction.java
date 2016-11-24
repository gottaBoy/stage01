package com.smt.webapp.weih5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import jxl.common.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.smt.entity.TAppUserInfo;
import com.smt.webapp.service.user.UserInfoService;
import com.smt.webapp.weih5.utils.H5SignUtil;
import com.smt.webapp.weih5.utils.H5WeixinHelper;
import com.smt.webapp.weih5.utils.H5WeixinUtils;
import com.smt.webapp.weih5.vo.H5ArtistBriefInfoVo;

public class H5IndexAction extends H5BaseAction {

	private static ConcurrentHashMap<String, List<H5ArtistBriefInfoVo>> INNER_CACHE = new ConcurrentHashMap<String, List<H5ArtistBriefInfoVo>>();
	private static final String KEY_ARTIST_LIST = "artistList";
	private static final String KEY_SUGGEST_ARTIST_LIST = "suggestArtistList";
	private static final String KEY_ALL_ARTIST_LIST = "allArtistList";

	@Autowired
	private UserInfoService userInfoService;

	private Logger logger = Logger.getLogger(H5IndexAction.class);

	public String indexPage() {

		HttpServletRequest request = ServletActionContext.getRequest();
		// Banner

		// Artist list (3)
		if (INNER_CACHE.size() == 0 || !INNER_CACHE.containsKey(KEY_ARTIST_LIST)) {
			List<TAppUserInfo> artists = new ArrayList<TAppUserInfo>();
			List<H5ArtistBriefInfoVo> artistList = new ArrayList<H5ArtistBriefInfoVo>();

			TAppUserInfo user1 = userInfoService.findUserInfoByUserId(10299);
			TAppUserInfo user2 = userInfoService.findUserInfoByUserId(10669);
			TAppUserInfo user3 = userInfoService.findUserInfoByUserId(1280);

			artists.add(user1);
			artists.add(user2);
			artists.add(user3);

			for (TAppUserInfo userInfo : artists) {
				H5ArtistBriefInfoVo briefInfoVo = new H5ArtistBriefInfoVo();
				briefInfoVo.setUserId(userInfo.getUserId());
				briefInfoVo.setNickName(userInfo.getNickName());
				briefInfoVo.setTags(StringUtils.isBlank(userInfo.getProfessionStr()) ? "" : userInfo.getProfessionStr().replace(",", " "));
				artistList.add(briefInfoVo);
			}
			INNER_CACHE.put(KEY_ARTIST_LIST, artistList);
		}

		if (INNER_CACHE.size() == 0 || !INNER_CACHE.containsKey(KEY_SUGGEST_ARTIST_LIST)) {
			List<TAppUserInfo> suggestArtists = userInfoService.getIndexUserSuggestion(0, 5);
			List<H5ArtistBriefInfoVo> suggestArtistList = new ArrayList<H5ArtistBriefInfoVo>();
			for (TAppUserInfo userInfo : suggestArtists) {
				H5ArtistBriefInfoVo briefInfoVo = new H5ArtistBriefInfoVo();
				briefInfoVo.setUserId(userInfo.getUserId());
				briefInfoVo.setNickName(userInfo.getNickName());
				briefInfoVo.setTags(StringUtils.isBlank(userInfo.getProfessionStr()) ? "" : userInfo.getProfessionStr().replace(",", " "));
				suggestArtistList.add(briefInfoVo);
			}
			INNER_CACHE.put(KEY_SUGGEST_ARTIST_LIST, suggestArtistList);
		}

		request.setAttribute("artistList", INNER_CACHE.get(KEY_ARTIST_LIST));
		request.setAttribute("suggestArtistList", INNER_CACHE.get(KEY_SUGGEST_ARTIST_LIST));

		request.setAttribute("appId", H5WeixinHelper.APP_ID);
		request.setAttribute("timestamp", H5WeixinUtils.TIMESTAMP);
		request.setAttribute("nonceStr", H5WeixinUtils.NONCESTR);
		request.setAttribute("signature", H5SignUtil.signForJsapiTicket("http://www.d15t.com/weih5/h5IndexPage.htm"));

		return SUCCESS;
	}

	public String indexArtistList() {
		HttpServletRequest request = ServletActionContext.getRequest();

		if (INNER_CACHE.size() == 0 || !INNER_CACHE.containsKey(KEY_ALL_ARTIST_LIST)) {
			List<TAppUserInfo> artists = userInfoService.getIndexUserSuggestion(0, 50);
			List<H5ArtistBriefInfoVo> artistList = new ArrayList<H5ArtistBriefInfoVo>();
			for (TAppUserInfo userInfo : artists) {
				H5ArtistBriefInfoVo briefInfoVo = new H5ArtistBriefInfoVo();
				briefInfoVo.setUserId(userInfo.getUserId());
				briefInfoVo.setNickName(userInfo.getNickName());
				briefInfoVo.setTags(StringUtils.isBlank(userInfo.getProfessionStr()) ? "" : userInfo.getProfessionStr().replace(",", " "));
				artistList.add(briefInfoVo);
			}
			INNER_CACHE.put(KEY_ALL_ARTIST_LIST, artistList);
		}

		request.setAttribute("artistList", INNER_CACHE.get(KEY_ALL_ARTIST_LIST));
		return SUCCESS;
	}
}
