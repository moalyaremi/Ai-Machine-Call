package com.aiansweringmachine.domain.model

enum class DemoCallScenario(
    val displayName: String,
    val callerName: String?,
    val phoneNumber: String,
    val topic: String,
    val priority: Priority,
    val summary: String,
    val followUpRequested: Boolean
) {
    KNOWN("متصل معروف", "محمد (Demo)", "+967711111111", "متابعة طلب سابق", Priority.NORMAL, "يريد معرفة حالة الطلب.", true),
    UNKNOWN("رقم مجهول", null, "+967722222222", "سبب اتصال غير معروف", Priority.NORMAL, "لم يتم تحديد سبب الاتصال بعد.", false),
    VIP("متصل VIP", "عميل VIP (Demo)", "+967733333333", "طلب عاجل", Priority.URGENT, "طلب تواصلًا عاجلًا اليوم.", true),
    SPAM("Spam محتمل", "Unknown Spam (Demo)", "+967744444444", "اتصال مشبوه", Priority.LOW, "تم تصنيف المكالمة كتجربة Spam محتمل.", false),
    BUSINESS("عميل تجاري", "شركة تجريبية", "+967755555555", "أوقات العمل والخدمات", Priority.HIGH, "يسأل عن ساعات العمل والخدمات المتاحة.", true)
}
