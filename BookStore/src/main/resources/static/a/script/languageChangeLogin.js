
i18next
    .use(i18nextBrowserLanguageDetector)
    .init({
        resources: {
            en: {
                translation: {
                    "title": "Login",
                    "emailPlaceholder": "Login",
                    "passwordPlaceholder": "Password",
                    "submitButton": "Log in"
                }
            },
            ru: {
                translation: {
                    "title": "Вход",
                    "emailPlaceholder": "Логин",
                    "passwordPlaceholder": "Пароль",
                    "submitButton": "Войти"
                }
            }
        },
        fallbackLng: 'ru',
        lng: localStorage.getItem('language') || 'ru',
        detection: {
            order: ['localStorage', 'navigator']
        }
    }, function(err, t) {
        updateContent();
    });


function updateContent() {
    document.getElementById('title-entry').textContent = i18next.t('title');
    document.getElementById('usernameLogin').placeholder = i18next.t('emailPlaceholder');
    document.getElementById('passwordLogin').placeholder = i18next.t('passwordPlaceholder');
    document.getElementById('submitButton').textContent = i18next.t('submitButton');
}


function toggleLanguage() {
    const currentLang = i18next.language;
    const newLang = currentLang === 'ru' ? 'en' : 'ru';
    i18next.changeLanguage(newLang, () => {
        localStorage.setItem('language', newLang);
        updateContent();
    });
}
