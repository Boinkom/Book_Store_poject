
i18next
    .use(i18nextBrowserLanguageDetector)
    .init({
        resources: {
            en: {
                translation: {
                    "title": "Registration",
                    "emailPlaceholder": "Email",
                    "loginPlaceholder": "Login",
                    "passwordPlaceholder": "Password",
                    "passwordPlaceholder2": "Repeat password",
                    "submitButton": "Register"
                }
            },
            ru: {
                translation: {
                    "title": "Регистрация",
                    "emailPlaceholder": "Почта",
                    "loginPlaceholder": "Логин",
                    "passwordPlaceholder": "Пароль",
                    "passwordPlaceholder2": "Повторите пароль",
                    "submitButton": "Зарегестрироваться"
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
    document.getElementById('title-reg').textContent = i18next.t('title');
    document.getElementById("emailReg").placeholder = i18next.t('emailPlaceholder');
    document.getElementById('usernameReg').placeholder = i18next.t('loginPlaceholder');
    document.getElementById('passwordReg1').placeholder = i18next.t('passwordPlaceholder');
    document.getElementById('passwordReg2').placeholder = i18next.t('passwordPlaceholder2');
    document.getElementById('RegButton').textContent = i18next.t('submitButton');
}


function toggleLanguage() {
    const currentLang = i18next.language;
    const newLang = currentLang === 'ru' ? 'en' : 'ru';
    i18next.changeLanguage(newLang, () => {
        localStorage.setItem('language', newLang);
        updateContent();
    });
}
