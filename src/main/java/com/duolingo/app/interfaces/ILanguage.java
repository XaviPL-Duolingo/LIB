package com.duolingo.app.interfaces;

import com.duolingo.app.model.Language;

import java.util.List;

public interface ILanguage {

    public List<Language> getAllLanguages();

    public Language getLanguageByID(int idLanguage);


}
