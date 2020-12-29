# 集成
## 拷贝aar到项目libs目录，build.gradle中使用下述方式引用库
```
dependencies {
    implementation fileTree(include: ['*.jar',"*.aar"], dir: 'libs')
    .....//其余略
}
```
## Androidmanifest.xml,加入sd卡读写权限（andorid 5.0以上需要动态申请sd卡读写权限!!!!!）
```
<application>
....
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

# 初始化

## 检测初始化

```
val ret = PESFaceDetect.init(this) 
```
## 特征初始化

```
val ret = PESFeature.init(this, PESFeature.Version.Mask) 
```

## 戴口罩判断初始化

```
val ret = PESMask.init(this)
``` 

## 人脸质量判断初始化

```
val ret = PESQuality.init(this)
``` 

# 检测
```
var result:List<FaceInfo> = PESFaceDetect.detect(
                    bgr,
                    bitmap.width,
                    bitmap.height,
                    SDKConstant.IMAGE_FORMAT_BGR
            ) //返回检测人脸结果
```
# 质量判断
```
val qualuty:Float = PESQuality.detect(result.first().rect,bgr,width, height, SDKConstant.IMAGE_FORMAT_BGR) //返回质量分数
```

# 判断是否戴口罩
```
val mask = PESMask.detect(result.first().landmark.raw,bgr,width,height,SDKConstant.IMAGE_FORMAT_BGR) //返回是否戴口罩分数
```

# 特征抽取
```
val feature:ByteArray = PESFeature.extract(result.first().landmark.raw,bgr,bitmap.width,bitmap.height,SDKConstant.IMAGE_FORMAT_BGR) //抽取landmark的特征值
```

# 特征比对
```
var pesfCompare: PESFCompare? = null
pesfCompare = PESFCompare()
pesfCompare!!.threshold = 0.65f
pesfCompare!!.maxResult = 3
PESFCompare.setMpThreads(4)
```

# 数据结构
### 参照docs目录下的javaDoc

# 其他
### 重要！1s or pro设备的pessk库需要用libs-1s的下的对应版本进行替换！