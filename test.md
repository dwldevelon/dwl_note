[TOC]
# 订单返券包技术方案

## 订单返券包
### 券包管理
* ![](./img/img_1.png)
* 优惠券包新增、编辑，发放方式可选类型增加"订单返券(ORC)"，后端无改动，配合前端联调。  
* 订单来源(orderSource),和券模板逻辑一致，来源可选项字典和券模板一致,后端无改动，配合前端联调。  
* 订单类型(orderType原型有遗漏)，和券模板逻辑一致，订单来源为线上或全部时，订单类型必选。后端无改动，配合前端联调。  
* 券包发放规则和券模板一致，后端无改动，配合前端联调。  
* 放周期固定为不限制、最大发放量固定0(不限制)、用户单日最多领取量0(不限制)，在订单返券包时，后端增加相应的校验逻辑。
* 相关接口： 

  |  接口名称   | 接口地址  | 请求方式 | 变动说明 |
  |  ----  | ----  | ---- | ----|
  | 优惠券模板新增  | /admin/coupontemplate/ | POST | 增加校验逻辑，参数无变更 |
  | 优惠券模板新增  | /admin/coupontemplate/ | PUT | 增加校验逻辑，参数无变更 |

### 订单返券包逻辑调整
* 如果是券包，返券结果为多个券码，orc_order表coupon_code字段由Long调整为String,多个券码用英文逗号分隔。调整相关的查询逻辑。
* 返券状态查询接口，调整券包是否核销的逻辑。
* 退货锁定时，如果为券包，锁定券包发放的所有优惠券。
* 废弃优惠券时，如果为券包，废弃券包发放的所有优惠券。
* 退货时，退还券包发放的所有优惠券。
* 影响接口： 

  |  接口名称   | 接口地址  | 请求方式 | 变动说明 |
  |  ----  | ----  | ---- | ----|
  | 订单返券(线上)  | /api/orderGiveCoupon/orderGiveCoupon | POST | 增加返券包支持，返回字段couponCode支持多个，couponName支持券包名称 |
  | 订单返券状态查询接⼝(线上)  | /api/orderGiveCoupon/queryOrderCouponRefundStatus | POST | 券包是否核销逻辑调整，couponCode券码支持多个，couponName支持券包名称 |
  | 锁定退货的优惠券商品信息⼝(线上)  | /api/orderGiveCoupon/lockOrderCouponProducts | POST | 支持锁定券包下的所有优惠券 |
  | 返券优惠券废弃接⼝(线上)  | /api/orderGiveCoupon/discardOrderCoupon | POST | 支持废弃券包下的所有优惠券 |
  | 优惠券退货结果通知接⼝  | /api/orderGiveCoupon/discardOrderCoupon | POST | 支持退还券包下的所有优惠券 |
  | 线下订单信息保存并返回返券信息  | /api/orderGiveCoupon/saveOfflineOrderGiveCouponInfo | POST | 同线上返券调整 |
  | 返券优惠券废弃接口(线下)  | /api/orderGiveCoupon/discardOrderCouponOfflineOrder | POST | 同线上返券调整 |
  | 订单返券的冲正接口(线下)  | /api/orderGiveCoupon/discardOrderCouponOfflineOrder | POST | 支持冲正券包 |
  | 订单返券(线下)  | /api/orderGiveCoupon/offlineOrderGiveCoupon | POST | 同线上返券调整 |

## 优惠券统筹
* ![](./img/img_2.png)
* 优惠券模板表coupon_template，增加统筹门店字段head_stores，保存优惠券模板或券包模板时，根据后台根据传入的可用门店自动计算，前端无感知。  
* 查询结果返回增加字段auth(0:可读;1:可读写)，前端用于判断是否显示编辑、审核等按钮。
* 查询增加参数auth(0:可读;1:可读写)，默认为0，手工发放优惠券传1。后端结合从passport获取当前用户的门店权限信息，查询预期的结果。
* 可统筹门店编码通过配置文件配置，本期需求仅配置1000百货事业部，配置后新增券模板或重启任务(历史数据)后生效。
* 增加任务，版本上线或修改可统筹门店配置后，手动执行修复coupon_template表历史数据。
* 相关接口： 

  |  接口名称   | 接口地址  | 请求方式 | 变动说明 |
  |  ----  | ----  | ---- | ----|
  | 优惠券列表(手工发券)  | /admin/coupontemplate/manualIssue/list | POST | 增加查询字段auth(0:可读;1:可读写) |
  | 优惠券模板管理列表  | /admin/coupontemplate/list | POST | 增加返回字段auth(0:可读;1:可读写) |
  | 优惠券模板审核列表  | /admin/coupontemplate/audit/list | POST | 增加返回字段auth(0:可读;1:可读写) |


## 优惠券明细手机号脱敏
* ![](./img/img.png)
* 查询结果和导出字段单独脱敏处理，不做全局调整。


```flow
st=>start: Start
op=>operation: Your Operation
cond=>condition: Yes or No?
e=>end
st->op->cond
cond(yes)->e
cond(no)->op
```





