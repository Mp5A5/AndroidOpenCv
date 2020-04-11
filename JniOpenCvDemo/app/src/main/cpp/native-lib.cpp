#include <jni.h>
#include "native-lib.h"


extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mp5a5_opencv_NativeLibUtils_bitmap2Gray(JNIEnv *env, jobject instance, jintArray pixels,
                                                 jint w, jint h) {
    jint *cur_array;

    jboolean isCopy = static_cast<jboolean>(false);

    cur_array = env->GetIntArrayElements(pixels, &isCopy);
    if (cur_array == NULL) {
        return 0;
    }

    Mat img(h, w, CV_8UC4, (unsigned char *) cur_array);

    cvtColor(img, img, CV_BGRA2GRAY);
    cvtColor(img, img, CV_GRAY2BGRA);

    int size = w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, (jint *) img.data);
    env->ReleaseIntArrayElements(pixels, cur_array, 0);
    return result;
}