# رفع المشروع إلى GitHub

ارفع محتويات هذا المجلد كاملة إلى المستودع، بحيث تكون الملفات التالية في الجذر:

```text
settings.gradle.kts
build.gradle.kts
gradle.properties
gradle/libs.versions.toml
app/
README.md
```

لا ترفع مجلدات `build` أو `.gradle` أو ملف `local.properties` أو أي مفاتيح/API secrets.

## إنشاء المستودع والرفع

من داخل مجلد المشروع:

```text
git init
git add .
git commit -m "Initialize AI Answering Machine foundation"
git branch -M main
git remote add origin https://github.com/YOUR_ACCOUNT/YOUR_REPOSITORY.git
git push -u origin main
```

بعد الرفع أرسل رابط المستودع. إذا كان Private، أرسل الملفات كـ ZIP أو امنح بيئة العمل صلاحية الوصول المناسبة.

## الفحص بعد الرفع

سيتم فحص المستودع بالترتيب التالي:

1. Gradle sync وdependency resolution.
2. `assembleDebug`.
3. Unit/instrumentation tests.
4. إصلاح أخطاء البناء والاختبارات.
5. إضافة Gradle wrapper رسمي عند توفر Gradle/Android Studio.
6. بدء PHASE 2 فقط بعد اعتماد الأساس.

## تفعيل Actions

الملف `.github/workflows/android-ci.yml` مضاف مسبقًا. بعد رفعه إلى GitHub:

1. افتح تبويب **Actions**.
2. اختر **Android CI**.
3. اضغط **Run workflow** للتشغيل اليدوي، أو نفّذ Push جديدًا.
4. بعد النجاح ستجد ملف APK في قسم **Artifacts** داخل نتيجة التشغيل.

إذا بقي تبويب Actions فارغًا، تأكد من أن مجلد `.github/workflows` رُفع كما هو، وأن Actions مفعّلة من Settings → Actions → General.
