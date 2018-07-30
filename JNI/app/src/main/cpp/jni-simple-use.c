//
// Created by barry on 2018/2/11.
//
#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include <android/log.h>

#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

//定义一个函数实现本地方法：helloFromC()
//env: 结构体二级指针，该结构体封装了大量的函数指针，可以帮助程序员实现某些常用功能
//type: 本地方法调用者的对象(MainActivity的对象)
JNIEXPORT jstring JNICALL
Java_com_barry_jni_JNIUtil_helloFromC(JNIEnv *env, jclass type) {

    // TODO
//    char cstr[] = "hello from c";
    char* cstr = "熊大和光头鹏达成成就，解锁36种姿势";
    //把C字符串转换成java字符串
    jstring jstr = (*env)->NewStringUTF(env, cstr);
    return jstr;
}

JNIEXPORT jint JNICALL
Java_com_barry_jni_JNIUtil_add(JNIEnv *env, jclass type, jint i, jint j) {

    // TODO
    return i + j;
}

JNIEXPORT jint JNICALL
Java_com_barry_jni_JNIUtil_encode(JNIEnv *env, jclass type, jint pwd) {

    // TODO
    return pwd + 3;
}

//JNIEXPORT jstring JNICALL
//Java_com_barry_jni_JNIUtil_encodeStr(JNIEnv *env, jobject obj, jstring str_, jint len) {
//    //把java字符串轉換成c字符串
//    char* cstr = Jstring2CStr(env, str_);
//    int i;
//    for (i = 0; i < len; i++) {
//        //這個是直接在它的地址上進行修改的，當然改變該地址上的值了
//        *(cstr + i) += 1;
//    }
//    return (*env)->NewStringUTF(env, cstr);
//}
//
//JNIEXPORT jstring JNICALL
//Java_com_barry_jni_JNIUtil_decodeStr(JNIEnv *env, jobject obj, jstring str_, jint len) {
//    //把java字符串轉換成c字符串
//    char* cstr = Jstring2CStr(env, str_);
//    int i;
//    for (i = 0; i < len; i++) {
//        *(cstr + i) -= 1;
//    }
//    return (*env)->NewStringUTF(env, cstr);
//}
//
//char*   Jstring2CStr(JNIEnv*   env,   jstring   jstr)
//{
//    char*   rtn   =   NULL;
//    //所有字符串的字节码文件都是相同的。
//    jclass   clsstring   =   (*env)->FindClass(env,"java/lang/String");
//    //方法中传递的参数
//    jstring   strencode   =   (*env)->NewStringUTF(env,"GB2312");
//    //获取方法
//    jmethodID   mid   =   (*env)->GetMethodID(env,clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
//    //执行该方法。
//    jbyteArray   barr=   (jbyteArray)(*env)->CallObjectMethod(env,jstr,mid,strencode); // String .getByte("GB2312");
//    //获取字符串的长度
//    jsize   alen   =   (*env)->GetArrayLength(env,barr);
//    //获取首地址
//    jbyte*   ba   =   (*env)->GetByteArrayElements(env,barr,JNI_FALSE);
//    //如果这个字符串组真实存在，则长度是大于0的。
//    if(alen   >   0)
//    {
//        //为其在内存中申请一个地址
//        rtn   =   (char*)malloc(alen + 1);         //"\0"
//        //将这个字符串放到这个练习的空间内。
//        memcpy(rtn,ba,alen);
//        rtn[alen]=0;
//    }
//    (*env)->ReleaseByteArrayElements(env,barr,ba,0);  //
//    return rtn;
//}

char*   Jstring2CStr(JNIEnv*   env,   jstring   jstr)
{
    char*   rtn   =   NULL;
    //所有字符串的字节码文件都是相同的。
    jclass   clsstring   =   (*env)->FindClass(env,"java/lang/String");
    //方法中传递的参数
    jstring   strencode   =   (*env)->NewStringUTF(env,"GB2312");
    //获取方法
    jmethodID   mid   =   (*env)->GetMethodID(env,clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
    //执行该方法。
    jbyteArray   barr=   (jbyteArray)(*env)->CallObjectMethod(env,jstr,mid,strencode); // String .getByte("GB2312");
    //获取字符串的长度
    jsize   alen   =   (*env)->GetArrayLength(env,barr);
    //获取首地址
    jbyte*   ba   =   (*env)->GetByteArrayElements(env,barr,JNI_FALSE);
    //如果这个字符串组真实存在，则长度是大于0的。
    if(alen   >   0)
    {
        //为其在内存中申请一个地址
        rtn   =   (char*)malloc(alen + 1);         //"\0"
        //将这个字符串放到这个练习的空间内。
        memcpy(rtn,ba,alen);
        rtn[alen]=0;
    }
    (*env)->ReleaseByteArrayElements(env,barr,ba,0);  //
    return rtn;
}

JNIEXPORT jstring JNICALL Java_com_barry_jni_JNIUtil_encodeStr
        (JNIEnv * env, jobject thiz, jstring pass, jint length){
    //把java字符串转换成c字符串
    char* cstr = Jstring2CStr(env, pass);

    int i;
    for(i = 0; i < length; i++){
        //这个是直接在它的地址上进行修改的，当然改变该地址上的值了。
        *(cstr + i) += 1;
    }
    return (*env)->NewStringUTF(env, cstr);
}


JNIEXPORT jstring JNICALL Java_com_barry_jni_JNIUtil_decodeStr
        (JNIEnv * env, jobject thiz, jstring pass, jint length){
    //把java字符串转换成c字符串
    char* cstr = Jstring2CStr(env, pass);

    int i;
    for(i = 0; i < length; i++){
        *(cstr + i) -= 1;
    }
    return (*env)->NewStringUTF(env, cstr);

}

JNIEXPORT void JNICALL
Java_com_barry_jni_JNIUtil_transmit(JNIEnv *env, jclass type, jintArray arry_) {

    //获取数组的长度
    //jsize       (*GetArrayLength)(JNIEnv*, jarray);
    jsize size = (*env)->GetArrayLength(env, arry_);

    //获取数组首地址
    //jint*       (*GetIntArrayElements)(JNIEnv*, jintArray, jboolean*);
    jint* arrp = (*env)->GetIntArrayElements(env, arry_, 0);

    int i;
    //把所有元素都+5
    for(i = 0; i < size; i++){
        *(arrp + i) += 5;
    }
    LOGD("%p", &arrp);
}

JNIEXPORT void JNICALL
Java_com_barry_jni_JNIUtil_callJava(JNIEnv *env, jobject obj) {

    // TODO
    LOGD("debug");
    //加載字節碼對象
//    jclass (*FindClass)(JNIEnv*, const char*);
    jclass clazz = (*env)->FindClass(env, "com/barry/jni/CallJavaActivity");

    //獲取方法
//    jmethodID (*GetMethod)(JNIEnv*, jclass, const char*, const char*);
    jmethodID  methodId = (*env)->GetMethodID(env, clazz, "showDialog", "(Ljava/lang/String;)V");

    //運行方法
//    void (*CallVoidMethod)(JNIEnv *, jobject, jmethodID, ...);
    (*env)->CallVoidMethod(env, obj, methodId, (*env)->NewStringUTF(env, "再次恭喜熊大和光頭鵬達成成就：第二個愛情結晶"));
}