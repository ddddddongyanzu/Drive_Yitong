// pages/register/register.js
const {
  get,
  post,
  baseurl
} = require('../../utils/request')
let app = getApp();
Page({
  data: {
    touxiang: 'https://manager.diandianxc.com/diandianxc/mrtx.png',
    icon_r: 'https://manager.diandianxc.com/mine/enter.png',
    modalHidden: true,
    information: [],
    address: "",
    // 价格
    prices: [],
    // 1代表c1，2代表c2
    car_type: 0,
    // 原价
    original_price: 0.00,
    // 现价
    current_price: 0.00,
    red_packet_open: false,
    // c1的优惠价格(点击优惠链接接收来的)
    c1_price: 0.00,
    // c2的优惠价格(点击优惠链接接收来的)
    c2_price: 0.00,
    uId: 0,
    // 代理的uid
    proxy_uid: 0,
    // 'none'表示没有优惠  ''表示是通过点击优惠链接进来的，有优惠。
    isDisplay: 'none',
    // 表单是否正确
    form_is_correct: false,
    form_is_correct_1: false,
    form_is_correct_2: false,
    form_is_correct_3: false,
    error_msg: "请填写信息哟~",
    //  是否是代理人发布优惠
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var value= wx.getStorageSync('user');
    console.log(value);
    wx.request({
      url: baseurl + '/price/selectPrice',
      success: (res) => {
        console.log("--------------------用户请求了价格--------------------");
        console.log(res.data);
        that.setData({
          prices: res.data
        });
        const eventChannel = this.getOpenerEventChannel();
        if (Object.keys(eventChannel).length > 0) {
          // 监听acceptDataFromOpenerPage事件，获取上一页面通过eventChannel传送到当前页面的数据
          eventChannel.on('acceptDataFromOpenerPage', function (data) {
            that.setData({
              car_type: data.id,
              isDisplay: 'none',
              original_price: res.data[data.id - 1].price
            });
          })
        }
        // 若果是通过分享的链接进来的，则设置优惠价格
        if (Object.keys(options).length > 0) {
          console.log("optins是----------", options);
          that.setData({
            c1_price: options.c1_price,
            c2_price: options.c2_price,
            proxy_uid: options.uId,
            isDisplay: ''
          });
          // 打开红包页
          that.open();
        }
      }
    })
  },

  // 获取价格
  getPrices() {
    let that = this;
    wx.request({
      url: baseurl + '/price/selectPrice',
      success: (res) => {
        console.log("--------------------用户请求了价格--------------------");
        console.log(res.data);
        that.setData({
          prices: res.data
        });
      }
    })
  },

  // c1  c2选择框切换
  radioChange(e) {
    console.log("用户选择了C" + e.detail.value);
    this.getPrices();
    this.setData({
      car_type: e.detail.value,
      original_price: this.data.prices[e.detail.value - 1].price
    });
    if (e.detail.value == 1) {
      this.setData({
        current_price: this.data.c1_price,
      })
    } else {
      this.setData({
        current_price: this.data.c2_price
      })
    }
  },


  //表单提交
  formSubmit(e) {
    // 全部验证通过
    if (this.data.form_is_correct_1 & this.data.form_is_correct_2 & this.data.form_is_correct_3) {
      this.setData({
        form_is_correct: true,
        error_msg: ""
      });
    } else {
      this.setData({
        form_is_correct: false,
      });
    }

    if (this.data.form_is_correct) {
      this.setData({
        information: e.detail.value,
        modalHidden: false
      });
    } else {
      this.showToast(this.data.error_msg, "error", 800);
    }
  },


  // 模态框取消（取消报名）
  modalCancel() {
    wx.showToast({
      title: '取消提交',
      icon: 'none'
    })
    this.setData({
      modalHidden: true,
    })
  },

  //确认报名
  modalConfirm: function (e) {
    let that = this;
    let pId;
    let payAmount = 0;
    if (this.data.isDisplay == '') {
      payAmount = this.data.current_price;
    } else {
      payAmount = this.data.original_price;
    }
    wx.getStorage({
      key: 'user',
      success:res=> {
        console.log(res.data.uId);
        let user = res.data;
        if (user.isRegister == 1) {
          console.log("--------------------用户重复报名--------------------");
          that.showToast("您已经报名了！无需重复报名！", "error", 2000);
          that.setData({
            modalHidden: true
          });
        } else {
          wx.request({
            url: baseurl + '/student/add',
            data: {
              student: that.data.information,
              uId: user.uId,
              carType: that.data.car_type,
              payAmount: payAmount,
              proxy_uid: that.data.proxy_uid
            },
            success() {
              wx.setStorage({
                key: "user",
                data: user
              })
              that.showToast("报名成功", "success", 1000);
              wx.navigateBack({})
              that.showToast("报名成功", "success", 1000);
              console.log("--------------------用户报名了，报名信息如下：--------------------");
              console.log(that.data.information);
            },
            fail(res) {
              that.showToast("报名成功", "success", 1000);
              wx.navigateBack({})
              that.showToast("报名成功", "success", 1000);
              console.log(res);
            }
          });
          that.setData({
            modalHidden: true
          })
        }
      },
      fail() {
        that.showToast('请先登录', 'error', 500);
        setTimeout(function () {
          wx.navigateTo({
            url: '../ucenter/login/login',
          })
        }, 200);
      }
    });
  },


  // 报名成功返回首页
  back() {
    let that = this;
    wx.navigateBack({
      success() {
        that.showToast("报名成功", "success", 1000);
      }
    })
  },

  showToast(title, icon, duration) {
    wx.showToast({
      title: title,
      icon: icon,
      duration: duration
    });
  },
  // 有些机型有bug，掉不出位置信息。
  getLocation() {
    let that = this;
    wx.getSetting({
      success(res) {
        console.log('res是否开启授权', res)
        if (!res.authSetting['scope.userLocation']) {
          wx.authorize({
            scope: 'scope.userLocation',
            success() {
              // console.log('前用户发起授权请求')
              that.chooseLocation();
            },
            fail() {
              // 用户点击不允许引导重新获取授权
              that.fetchAgainLocation()
            }
          })
        } else {
          // 已经授权了就会直接进入地图
          that.chooseLocation();
        }
      }
    })
  },
  chooseLocation() {
    wx.chooseLocation({
      success: (res) => {
        console.log(res);
        if (res.address != "") {
          this.setData({
            address: res.address,
            form_is_correct_3: true,
          });
        }
      },
    })
  },

  fetchAgainLocation() {
    let that = this
    wx.getSetting({
      success: (res) => {
        var statu = res.authSetting;
        if (!statu['scope.userLocation']) {
          wx.showModal({
            title: '是否授权当前位置',
            content: '需要获取您的地理位置，请确认授权，否则地图功能将无法使用',
            success: (tip) => {
              if (tip.confirm) {
                wx.openSetting({
                  success: (data) => {
                    if (data.authSetting["scope.userLocation"] === true) {
                      wx.showToast({
                        title: '授权成功',
                        icon: 'success',
                        duration: 1000
                      })
                      wx.chooseLocation({
                        success: res => {
                          console.log('打开地图选择确定', res)
                        }
                      })
                    } else {
                      wx.showToast({
                        title: '授权失败',
                        icon: 'success',
                        duration: 1000
                      })
                    }
                  },
                  fail: () => {},
                  complete: () => {}
                });
              }
            }
          })
        }
      },
      fail: () => {},
      complete: () => {}
    })
  },
  //  表单验证
  formCheck(e) {
    // let form_is_correct_4=false;
    // num参数
    console.log(e);
    let vaule = e.detail.value;
    let num = e.currentTarget.dataset.num;
    console.log(num);
    switch (num) {
      // 姓名验证
      case "1": {
        console.log("姓名验证");
        var reg = /^[A-Za-z0-9\u4e00-\u9fa5\.·]{1,10}$/;
        if (!reg.test(vaule)) {
          console.log("姓名格式错误");
          this.showToast("姓名格式错误", "error", 1000);
          this.setData({
            error_msg: "姓名格式错误",
            form_is_correct_1: false
          });
        } else {
          this.setData({
            form_is_correct_1: true
          });
        }
        break;
      }
      // 电话验证
      case "2": {
        console.log("电话验证");
        var reg = /^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/;
        if (!reg.test(vaule)) {
          console.log("电话格式错误");
          this.showToast("电话格式错误", "error", 1000);
          // form_is_correct_2=false
          this.setData({
            error_msg: "电话格式错误",
            form_is_correct_2: false
          });
        } else {
          this.setData({
            form_is_correct_2: true
          });
        }
        break;
      }
      // 地址验证
      case "3": {
        console.log("地址验证");
        if (vaule == "") {
          this.setData({
            error_msg: "请输入地址",
            form_is_correct_3: false
          });
        } else {
          this.setData({
            form_is_correct_3: true
          });
        }
        break;
      }
      default: {
        console.log("失败");
      }
    }
  },

  // 关闭红包弹窗
  close() {
    this.setData({
      red_packet_open: false
    });
    wx.showToast({
      title: '优惠领取成功',
      icon: "success",
      duration: 1500
    })
  },

  // 红包打开
  // 关闭红包弹窗
  open() {
    this.setData({
      red_packet_open: true
    });
  }

})