
i18next
    .use(i18nextBrowserLanguageDetector)
    .init({
        resources: {
            en: {
                translation: {
                    "title": "Book store",
                    "submitButton": "Add to cart"
                }
            },
            ru: {
                translation: {
                    "title": "Магазин книг",
                    "submitButton": "В корзину"
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
    document.getElementById('main-title').textContent = i18next.t('title');
    const submitButtons = document.querySelectorAll('.href-main');
    submitButtons.forEach(button => {
        button.textContent = i18next.t('submitButton');
    });
}


function toggleLanguage() {
    const currentLang = i18next.language;
    const newLang = currentLang === 'ru' ? 'en' : 'ru';
    i18next.changeLanguage(newLang, () => {
        localStorage.setItem('language', newLang);
        updateContent();
    });
}
