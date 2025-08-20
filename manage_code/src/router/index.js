	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import yuangong from '@/views/yuangong/list'
	import dakaxinxi from '@/views/dakaxinxi/list'
	import jijianxinxi from '@/views/jijianxinxi/list'
	import zhandianxinxi from '@/views/zhandianxinxi/list'
	import qianshouxinxi from '@/views/qianshouxinxi/list'
	import wupinruku from '@/views/wupinruku/list'
	import syslog from '@/views/syslog/list'
	import yuyuequjian from '@/views/yuyuequjian/list'
	import qujianxinxi from '@/views/qujianxinxi/list'
    import menu_manage from '@/views/menu_manage/list'
	import users from '@/views/users/list'
	import kuaidipeisong from '@/views/kuaidipeisong/list'
	import yunshuxinxi from '@/views/yunshuxinxi/list'
	import yonghu from '@/views/yonghu/list'
	import wupinchuku from '@/views/wupinchuku/list'
	import messages from '@/views/messages/list'
	import kaoqindaka from '@/views/kaoqindaka/list'
	import yuangongRegister from '@/views/yuangong/register'
	import yuangongCenter from '@/views/yuangong/center'
	import yonghuRegister from '@/views/yonghu/register'
	import yonghuCenter from '@/views/yonghu/center'
	import usersCenter from '@/views/users/center'

export const routes = [{
		path: '/login',
		name: 'login',
        meta: { title: '登录' },
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
        meta: { title: '首页' },
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: 'home',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true,
                title: '首页'
			}
		}, {
			path: '/updatepassword',
			name: 'updatepassword',
            meta: { title: '修改密码' },
			component: () => import('../views/updatepassword.vue')
		}

		,{
			path: '/yuangongCenter',
			name: 'yuangongCenter',
            meta: { title: '员工个人中心' },
			component: yuangongCenter
		}
		,{
			path: '/yonghuCenter',
			name: 'yonghuCenter',
            meta: { title: '用户个人中心' },
			component: yonghuCenter
		}
		,{
			path: '/usersCenter',
			name: 'usersCenter',
            meta: { title: '管理员个人中心' },
			component: usersCenter
		}
		,{
			path: '/yuangong',
			name: 'yuangong',
            meta: { title: '员工' },
			component: yuangong
		}
		,{
			path: '/dakaxinxi',
			name: 'dakaxinxi',
            meta: { title: '打卡信息' },
			component: dakaxinxi
		}
		,{
			path: '/jijianxinxi',
			name: 'jijianxinxi',
            meta: { title: '寄件信息' },
			component: jijianxinxi
		}
		,{
			path: '/zhandianxinxi',
			name: 'zhandianxinxi',
            meta: { title: '站点信息' },
			component: zhandianxinxi
		}
		,{
			path: '/qianshouxinxi',
			name: 'qianshouxinxi',
            meta: { title: '签收信息' },
			component: qianshouxinxi
		}
		,{
			path: '/wupinruku',
			name: 'wupinruku',
            meta: { title: '物品入库' },
			component: wupinruku
		}
		,{
			path: '/syslog',
			name: 'syslog',
            meta: { title: '操作日志' },
			component: syslog
		}
		,{
			path: '/yuyuequjian',
			name: 'yuyuequjian',
            meta: { title: '预约取件' },
			component: yuyuequjian
		}
		,{
			path: '/qujianxinxi',
			name: 'qujianxinxi',
            meta: { title: '取件信息' },
			component: qujianxinxi
		}
        ,{
            path: '/menu',
            name: 'menu',
            meta: { title: '菜单权限管理' },
            component: menu_manage
        }
		,{
			path: '/users',
			name: 'users',
            meta: { title: '管理员' },
			component: users
		}
		,{
			path: '/kuaidipeisong',
			name: 'kuaidipeisong',
            meta: { title: '快递配送' },
			component: kuaidipeisong
		}
		,{
			path: '/yunshuxinxi',
			name: 'yunshuxinxi',
            meta: { title: '运输信息' },
			component: yunshuxinxi
		}
		,{
			path: '/yonghu',
			name: 'yonghu',
            meta: { title: '用户' },
			component: yonghu
		}
		,{
			path: '/wupinchuku',
			name: 'wupinchuku',
            meta: { title: '物品出库' },
			component: wupinchuku
		}
		,{
			path: '/messages',
			name: 'messages',
            meta: { title: '留言板' },
			component: messages
		}
		,{
			path: '/kaoqindaka',
			name: 'kaoqindaka',
            meta: { title: '考勤打卡' },
			component: kaoqindaka
		}
		]
	},
	{
		path: '/yuangongRegister',
		name: 'yuangongRegister',
        meta: { title: '员工注册' },
		component: yuangongRegister
	},
	{
		path: '/yonghuRegister',
		name: 'yonghuRegister',
        meta: { title: '用户注册' },
		component: yonghuRegister
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
