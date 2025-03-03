// app.js
const {
  baseurl
} = require('./utils/request')
App({
  onLaunch() {
//连接云数据库
 if (!wx.cloud) {
  console.error('请使用 2.2.3 或以上的基础库以使用云能力')
} else {
  wx.cloud.init({
    // env 参数说明：
    //   env 参数决定接下来小程序发起的云开发调用（wx.cloud.xxx）会默认请求到哪个云环境的资源
    //   此处请填入环境 ID, 环境 ID 可打开云控制台查看
    //   如不填则使用默认环境（第一个创建的环境）
    env: 'cloud1-1gbozsxp675ec0ed',
    traceUser: true,
  })
}
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    this.getUser();
  },
  globalData: {
    userInfo: null
  },
  getUser() {
    wx.getStorage({
      key: 'user',
      success(res) {
        console.log(res.data.uId);
        wx.request({
          url: baseurl+'/user/selectOne?id=' + res.data.uId,
          success(res2) {
            console.log(res2.data);
            // wx.setStorageSync("user", res2.data);
          }
        })
      }
    });
  }

})