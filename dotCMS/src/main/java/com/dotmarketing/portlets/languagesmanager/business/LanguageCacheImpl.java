package com.dotmarketing.portlets.languagesmanager.business;

import java.util.List;

import com.dotmarketing.business.CacheLocator;
import com.dotmarketing.business.DotCacheAdministrator;
import com.dotmarketing.business.DotCacheException;
import com.dotmarketing.portlets.languagesmanager.model.Language;
import com.dotmarketing.portlets.languagesmanager.model.LanguageKey;
import com.dotmarketing.util.Logger;

/**
 * @author David
 */
public class LanguageCacheImpl extends LanguageCache {

	private static final String LANG_404_STR = "LANG__404";
	static final String ALL_LANGUAGES_KEY="ALL_LANGUAGES_KEY";
	static final String DEFAULT_LANGUAGE = "DEFAULT_LANGUAGE";
	public static Language LANG_404 = new Language(-1,
			LANG_404_STR, LANG_404_STR, LANG_404_STR,
			LANG_404_STR);

	@Override
    public List<Language> getLanguages()  {
	
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
    	
    	
		try {
			return (List<Language>) cache.get( ALL_LANGUAGES_KEY, getPrimaryGroup());
		} catch (DotCacheException e) {
			return null;
		}
    	
    	
    }
    public void putLanguages(List<Language> languages) {
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
    	cache.put(ALL_LANGUAGES_KEY, languages, getPrimaryGroup());
    	
    }

	public void clearLanguages() {
		DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
		cache.remove(ALL_LANGUAGES_KEY, getPrimaryGroup());
	}

	public Language getDefaultLanguage(){
		DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
		Language defaultLang = LANG_404;
		try {
			defaultLang = (Language)cache.get(DEFAULT_LANGUAGE, getPrimaryGroup());
			if(null == defaultLang){
			   cache.put(DEFAULT_LANGUAGE, LANG_404, getPrimaryGroup());
			   defaultLang = LANG_404;
			}
		} catch (DotCacheException e) {
			Logger.debug(LanguageCacheImpl.class,"Default Language not found in Cache.", e);
		}
		return defaultLang;
	}

	public void setDefaultLanguage(Language defaultLanguage){
		DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
		cache.put(DEFAULT_LANGUAGE, defaultLanguage, getPrimaryGroup());
	}

    public void addLanguage(Language l) {
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
		long id = l.getId();
        String idSt = String.valueOf(l.getId());
        String languageKey = l.getLanguageCode() + "-" + l.getCountryCode();
		cache.put(getPrimaryGroup() + id, l, getPrimaryGroup());
        cache.put(getPrimaryGroup() + idSt, l, getPrimaryGroup());
        cache.put(getPrimaryGroup() + languageKey, l, getPrimaryGroup());
		cache.remove(ALL_LANGUAGES_KEY, getPrimaryGroup());

	}

	@Override
	public void add404Language(final String languageCode, String countryCode) {
		DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
		String languageKey = languageCode + "-" + countryCode;
		cache.put(getPrimaryGroup() + languageKey, LANG_404, getPrimaryGroup());
	}
    
    public Language getLanguageById(long id){
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
    	Language f = null;
    	try{
    		f = (Language) cache.get(getPrimaryGroup() + id,getPrimaryGroup());
    	}catch (DotCacheException e) {
			Logger.debug(LanguageCacheImpl.class,"Cache Entry not found", e);
    	}
        return f;
	}

    public Language getLanguageById(String id) {
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
    	Language f = null;
    	try{
    		f = (Language) cache.get(getPrimaryGroup() + id,getPrimaryGroup());
    	}catch (DotCacheException e) {
			Logger.debug(LanguageCacheImpl.class,"Cache Entry not found", e);
    	}
        return f;
    }

    public Language getLanguageByCode(String languageCode, String countryCode) {
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
        String languageKey = languageCode + "-" + countryCode;
        languageKey = languageKey.toLowerCase();
        Language l = null;
        try{
        	l = (Language) cache.get(getPrimaryGroup() + languageKey,getPrimaryGroup());
        }catch (DotCacheException e) {
			Logger.debug(LanguageCacheImpl.class,"Cache Entry not found", e);
    	}
        
        return l;
    }

    public boolean hasLanguage (String id) {
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
        Language l = null;
    	try{
    		l = (Language) cache.get(getPrimaryGroup() + id,getPrimaryGroup());
    	}catch (DotCacheException e) {
			Logger.debug(LanguageCacheImpl.class,"Cache Entry not found", e);
    	}
        return l != null;
    }
    
    public boolean hasLanguage (long id) {
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
        Language l = null;
    	try{
    		l = (Language) cache.get(getPrimaryGroup() + id,getPrimaryGroup());
    	}catch (DotCacheException e) {
			Logger.debug(LanguageCacheImpl.class,"Cache Entry not found", e);
    	}
        return l != null;
    }
    
    public boolean hasLanguage (String languageCode, String countryCode) {
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
        String languageKey = languageCode + "-" + countryCode;
        Language l = null;
        try{
        	l = (Language) cache.get(getPrimaryGroup() + languageKey,getPrimaryGroup());
        }catch (DotCacheException e) {
			Logger.debug(LanguageCacheImpl.class,"Cache Entry not found", e);
    	}
        return l != null;
    }
    
    public void removeLanguage(Language l){
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
        long id = l.getId();
        String idSt = String.valueOf(l.getId());
        String languageKey = l.getLanguageCode() + "-" + l.getCountryCode();
        languageKey =  languageKey.toLowerCase();
        cache.remove(getPrimaryGroup() + id,getPrimaryGroup());
        cache.remove(getPrimaryGroup() + idSt,getPrimaryGroup());
        cache.remove(getPrimaryGroup() + languageKey,getPrimaryGroup());
		cache.remove(ALL_LANGUAGES_KEY, getPrimaryGroup());
	}

    public void clearCache(){
		DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
	    //clear the cache
	    cache.flushGroup(getPrimaryGroup());
	}
	public String[] getGroups() {
    	String[] groups = {getPrimaryGroup()};
    	return groups;
    }
    
    public String getPrimaryGroup() {
    	return "LanguageCacheImpl";
    }

	@Override
	public void setLanguageKeys(String langCode, String countryCode, List<LanguageKey> keys) {
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
        String languageKey = getPrimaryGroup() + "_Keys_" + (countryCode != null?langCode + "_" + countryCode:langCode);
        cache.put(languageKey, keys, getPrimaryGroup());
	}    

	@Override
	public void removeLanguageKeys(String langCode, String countryCode) {
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
        String languageKey = getPrimaryGroup() + "_Keys_" + (countryCode != null?langCode + "_" + countryCode:langCode);
        cache.remove(languageKey, getPrimaryGroup());
	}    

	@Override
	public List<LanguageKey> getLanguageKeys(String langCode, String countryCode) throws DotCacheException {
    	DotCacheAdministrator cache = CacheLocator.getCacheAdministrator();
        String languageKey = getPrimaryGroup() + "_Keys_" + (countryCode != null?langCode + "_" + countryCode:langCode);
        return (List<LanguageKey>) cache.get(languageKey, getPrimaryGroup());
	}    
}
