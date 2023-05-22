# 使用者請假系統

# MA000. 代碼檔查詢

## 串接格式

```
URL：localhost:8080/syscode
METHOD：POST
```

```json
上行電文:
{
   "header":{
       "sys":"HR-SYS",
       "api":"MA000",
       "time":1641275286261
   },
   "body":{
       "gp": "ZIP"
   }
}
```

```json
下行電文
{
    "header": {
       "sys":"HR-SYS",
       "api":"MA000",
       "time":1641275286261,
       "code": "0000",
       "desc": "查詢成功"
    },
    "body": {
        "gp": "ZIP",
        "data":[
            {
                "code"："100",
                "cname":"臺北市中正區"
            },
            {
                "code"："103",
                "cname":"臺北市大同區"
            },
            {
                "code"："104",
                "cname":"臺北市中山區"
            }
        ]
    }
}
```

|代碼|訊息|說明|
|-|--|--|
|0000|查詢成功||
|0001|查無資料||
|9999|系統發生異常，請聯絡系統管理員||

# MA001. 使用者登入

## 串接格式

```
URL：localhost:8080/login
METHOD：POST
```

```json
上行電文:
{
   "header":{
       "sys":"HR-SYS",
       "api":"MA001",
       "time":1641275286261
   },
   "body":{
       "iden": "A123456789",
       "pwd": "A123456"
   }
}
```

```json
下行電文
{
    "header": {
       "sys":"HR-SYS",
       "api":"MA001",
       "time":1641275286261,
       "code": "0000",
       "desc": "登入成功"
    },
    "body": {
        "token": "f739ab41-5608-441c-ba28-cb00c7be294e"
    }
}
```

|代碼|訊息|說明|
|-|--|--|
|0000|登入成功||
|0001|查無使用者||
|0002|密碼錯誤||
|9999|系統發生異常，請聯絡系統管理員||


# MA002. 使用者資料查詢

## 串接格式

```
URL：localhost:8080/user
METHOD：POST
```

```json
上行電文:
{
   "header":{
       "sys":"HR-SYS",
       "api":"MA002",
       "time":1641275286261
   },
   "body":{
       "token": "f739ab41-5608-441c-ba28-cb00c7be294e"
   }
}
```

```json
下行電文
{
    "header": {
       "sys":"HR-SYS",
       "api":"MA002",
       "time":1641275286261,
       "code": "0000",
       "desc": "查詢成功"
    },
    "body": {
        "iden": "A123***789",
        "cname": "張三",
        "ename": "CHANGE",
        "birth": "1990-01-04",
        "sex": "男性"
    }
}
```

|代碼|訊息|說明|
|-|--|--|
|0000|查詢成功||
|0001|查無登入資訊||
|0002|登入時間逾期||
|9999|系統發生異常，請聯絡系統管理員||

# MA003. 使用者變更密碼

## 串接格式

```
URL：localhost:8080/changepwd
METHOD：POST
```

```json
上行電文:
{
   "header":{
       "sys":"HR-SYS",
       "api":"MA003",
       "time":1641275286261
   },
   "body":{
       "token": "f739ab41-5608-441c-ba28-cb00c7be294e",
       "pwd":"abcd1234"
   }
}
```

```json
下行電文
{
    "header": {
       "sys":"HR-SYS",
       "api":"MA003",
       "time":1641275286261,
       "code": "0000",
       "desc": "變更密碼成功"
    }
}
```

|代碼|訊息|說明|
|-|--|--|
|0000|變更密碼成功||
|0001|查無登入資訊||
|0002|登入時間逾期||
|0003|變更密碼失敗||
|9999|系統發生異常，請聯絡系統管理員||

# MA004. 使用者資料查詢（明細）

## 串接格式

```
URL：localhost:8080/user/all
METHOD：POST
```

```json
上行電文:
{
   "header":{
       "sys":"HR-SYS",
       "api":"MA004",
       "time":1641275286261
   },
   "body":{
       "token": "f739ab41-5608-441c-ba28-cb00c7be294e"
   }
}
```

```json
下行電文
{
    "header": {
       "sys":"HR-SYS",
       "api":"MA004",
       "time":1641275286261,
       "code": "0000",
       "desc": "查詢成功"
    },
    "body": {
        "iden": "A123***789",
        "cname": "張三",
        "ename": "CHANGE",
        "age": 31, // 以出生日期計算年齡
        "birth": "1990-01-04",
        "sex": "男性",
        "address":{
            "domicile": "110 台北市 基隆路 XXXX",   //戶籍地址
            "contact": "110 台北市 基隆路 XXXX"     //聯絡地址
        }
    }
}
```

|代碼|訊息|說明|
|-|--|--|
|0000|查詢成功||
|0001|查無登入資訊||
|0002|登入時間逾期||
|9999|系統發生異常，請聯絡系統管理員||

# MA005. 使用者假別查詢

## 串接格式

```
URL：localhost:8080/user/leave
METHOD：POST
```

```json
上行電文:
{
   "header":{
       "sys":"HR-SYS",
       "api":"MA005",
       "time":1641275286261
   },
   "body":{
       "token": "f739ab41-5608-441c-ba28-cb00c7be294e",
       "year": 2022
   }
}
```

```json
下行電文
{
    "header": {
       "sys":"HR-SYS",
       "api":"MA005",
       "time":1641275286261,
       "code": "0000",
       "desc": "查詢成功"
    },
    "body": {
        "iden": "A123***789",
        "cname": "張三",
        "year": 2022,
        "leaves":[
            {
                "type": "A",
                "typeDesc": "特休",
                "ownHours": 80,
                "applyHours": 20,
                "totalHours": 60
            },
            {
                "type": "B",
                "typeDesc": "補休",
                "ownHours": 20,
                "applyHours": 5,
                "totalHours": 15
            },
            {
                "type": "C",
                "typeDesc": "事假",
                "ownHours": 0,
                "applyHours": 20,
                "totalHours": -20
            }
        ]
    }
}
```

|代碼|訊息|說明|
|-|--|--|
|0000|查詢成功||
|0001|查無登入資訊||
|0002|登入時間逾期||
|0003|查無假別資料||
|9999|系統發生異常，請聯絡系統管理員||


# MA006. 使用者假別明細查詢

## 串接格式

```
URL：localhost:8080/user/leave/dtl
METHOD：POST
```

```json
上行電文:
{
   "header":{
       "sys":"HR-SYS",
       "api":"MA006",
       "time":1641275286261
   },
   "body":{
       "token": "f739ab41-5608-441c-ba28-cb00c7be294e",
       "year": 2022
   }
}
```

```json
下行電文
{
    "header": {
       "sys":"HR-SYS",
       "api":"MA006",
       "time":1641275286261,
       "code": "0000",
       "desc": "查詢成功"
    },
    "body": {
        "iden": "A123***789",
        "cname": "張三",
        "year": 2022,
        "leaves":[
            {
                "type": "A",
                "typeDesc": "特休",
                "ownHours": 80,
                "applyHours": 0,
                "beginTime": null,
                "endTime": null,
                "remark": "系統匯入",
                "creTime": "2022-01-01 00:00:00"
            },
            {
                "type": "A",
                "typeDesc": "特休",
                "ownHours": 0,
                "applyHours": 8,
                "beginTime": "2022-01-05 08:00",
                "endTime": "2022-01-05 18:00",,
                "remark": "請假（特休）",
                "creTime": "2022-01-05 07:10:22"
            },
            {
                "type": "C",
                "typeDesc": "事假",
                "ownHours": 0,
                "applyHours": 8,
                "beginTime": "2022-01-05 08:00",
                "endTime": "2022-01-05 18:00",,
                "remark": "請假（事假）",
                "creTime": "2022-01-05 07:10:22"
            }
        ]
    }
}
```

|代碼|訊息|說明|
|-|--|--|
|0000|查詢成功||
|0001|查無登入資訊||
|0002|登入時間逾期||
|0003|查無明細資料||
|9999|系統發生異常，請聯絡系統管理員||


# MA007. 使用者請假

## 串接格式

```
URL：localhost:8080/user/leave/apply
METHOD：POST
```

```json
上行電文:
{
   "header":{
       "sys":"HR-SYS",
       "api":"MA007",
       "time":1641275286261
   },
   "body":{
       "token": "f739ab41-5608-441c-ba28-cb00c7be294e",
       "userId": "A123456789", 
       "type": "A",
       "hours": 8,
       "beginTime": "2022-01-02 09:00",
       "endTime": "2022-01-02 18:00"
   }
}
```

```json
下行電文(成功)
{
    "header": {
       "sys":"HR-SYS",
       "api":"MA007",
       "time":1641275286261,
       "code": "0000",
       "desc": "請假成功"
    }
}

下行電文(失敗)
{
    "header": {
       "sys":"HR-SYS",
       "api":"MA006",
       "time":1641275286261,
       "code": "1001",
       "desc": "請假失敗，假別（特休）時數不足"
    }
}
```

|代碼|訊息|說明|
|-|--|--|
|0000|查詢成功||
|0001|查無登入資訊||
|0002|登入時間逾期||
|1001|請假失敗，假別（特休）時數不足||
|9999|系統發生異常，請聯絡系統管理員||


# MA008. 匯入國定假日主檔

## 串接格式

```
URL：localhost:8080/holiday
METHOD：POST
```

```json
上行電文:
{
   "header":{
       "sys":"HR-SYS",
       "api":"MA008",
       "time":1641275286261
   },
   "body":{
       "sys": "GOV",
       "year":2002
   }
}
```

```json
下行電文(成功)
{
    "header": {
       "sys":"HR-SYS",
       "api":"MA008",
       "time":1641275286261,
       "code": "0000",
       "desc": "執行成功"
    }
}

```

|代碼|訊息|說明|
|-|--|--|
|0000|執行成功||
|1001|執行失敗||
|9999|系統發生異常，請聯絡系統管理員||
