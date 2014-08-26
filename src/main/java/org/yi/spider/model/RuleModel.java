package org.yi.spider.model;

public class RuleModel {

    private String method;
    private String options;
    private String pattern;
    private String regexName;
    private String filterPattern;

    public static class RegexNamePattern {

    	public static final String GET_SITE_URL = "GetSiteUrl";
    	public static final String GET_SITE_NAME = "GetSiteName";
        public static final String GET_SITE_CHARSET = "GetSiteCharset";
        public static final String NOVEL_LIST_URL = "NovelListUrl";
        public static final String NOVELLIST_GETNOVELKEY = "NovelList_GetNovelKey";
        public static final String NOVEL_URL = "NovelUrl";
        public static final String NOVEL_NAME = "NovelName";
        public static final String NOVEL_AUTHOR = "NovelAuthor";
        public static final String LAGER_SORT = "LagerSort";
        public static final String SMALL_SORT = "SmallSort";
        public static final String NOVEL_INTRO = "NovelIntro";
        public static final String NOVEL_KEYWORD = "NovelKeyword";
        public static final String NOVEL_DEGREE = "NovelDegree";
        public static final String NOVEL_COVER = "NovelCover";
        public static final String NOVEL_DEFAULT_COVER_URL = "NovelDefaultCoverUrl";
        public static final String NOVELINFO_GETNOVELPUBKEY = "NovelInfo_GetNovelPubKey";
        public static final String PUBINDEX_URL = "PubIndexUrl";
        public static final String PUBVOLUME_SPLIT = "PubVolumeSplit";
        public static final String PUBVOLUME_NAME = "PubVolumeName";
        public static final String PUBCHAPTER_NAME = "PubChapterName";
        public static final String PUBCHAPTER_GETCHAPTERKEY = "PubChapter_GetChapterKey";
        public static final String PUBCONTENT_URL = "PubContentUrl";
        public static final String PUBCONTENT_TEXT = "PubContentText";
        public static final String PUB_INDEX_ERR = "PubIndexErr";
        public static final String PUB_INDEX_CONTENT =  "PubIndexContent";
    }

    public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getRegexName() {
		return regexName;
	}

	public void setRegexName(String regexName) {
		this.regexName = regexName;
	}

	public String getFilterPattern() {
		return filterPattern;
	}

	public void setFilterPattern(String filterPattern) {
		this.filterPattern = filterPattern;
	}

	@Override
    public String toString() {
        return "Rule [method=" + method + ", options=" + options + ", pattern=" + pattern + ", regexName=" + regexName
                + "]";
    }

}
