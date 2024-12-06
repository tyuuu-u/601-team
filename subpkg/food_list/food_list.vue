<template>
	<view>
		<!-- 店铺头部信息展示 -->
		<view>
			<shop-head :shopInfo="shopInfo"></shop-head>
		</view>

		<!-- 分段器,分为菜品和评论,两个部分 -->
		<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" styleType="text"
			activeColor="#E67E22"></uni-segmented-control>
		<view class="content">
			<!-- 菜品分类展示页面 -->
			<view v-show="current === 0">
				<view class="scroll-view-container">
					<!-- 左侧滑动区域 -->
					<scroll-view class="left-scroll-view" scroll-y="true" :style="{height: wh + 'px'}">
						<block v-for="(item, i) in cateFoodsList" :key="i">
							<view :class="['left-scroll-view-item', i === active ? 'active' : '']"
								@click="activeChanged(i)">
								{{item.cateName}}
							</view>
						</block>
					</scroll-view>

					<!-- 右侧滑动区域 -->
					<scroll-view class="right-scroll-view" :scroll-top="scrollTop" scroll-y="true"
						:style="{height: wh + 'px'}">
						<view class="cate-lv2" v-for="(item2, i2) in foodsList" :key="i2" @click="goToDetail(item2.id)">

							<!-- 		<navigator :url="'../food_detail/food_detail?foodId='+item2.id">
							</navigator> -->
							<shop-food :food="item2" :showAddOrNum="true" ></shop-food>
						</view>
					</scroll-view>
				</view>
				<!-- 底部导航栏 -->
				<view>
					<van-submit-bar :price="currentShopTotalPrice" button-text="settle" @submit="onSubmit">
						<view style="display: flex;margin-right: auto;">

							<van-icon name="orders-o" :info="currentShopCartTotal" size="30px" @click="showPopup" />
						</view>
					</van-submit-bar>
				</view>
			</view>
		</view>


		<!-- 评论页面 -->
		<view v-show="current === 1">
			<view v-if="commentList.length===0">There are no reviews for this shop</view>
			<!-- 展示评论 -->
			<view v-else v-for="(comment,i) in commentList">
				<uni-card :title="comment.name" :key="i" :extra="comment.createDate" :is-shadow="false">
					<text>
						<!-- 		<text class="name-class">
							TA说：
						</text> -->
						{{comment.content}}
					</text>
					<view v-if="comment.isReply" class="divider" />
					<view class="reply">
						<text v-if="comment.isReply">
							<text class="name-class">
								Shop Owner Response:
							</text>
							{{comment.reply}}
						</text>
					</view>
				</uni-card>
			</view>
		</view>

		<!-- 弹出层 展示背包菜品 -->
		<view class="nav-popup">
			<uni-popup ref="popup" type="bottom" background-color="#fff" safe-area:true>
				<view class="nav-popup-items">
					<scroll-view scroll-y="true" style="height: 300px;">
						<view v-if="!currentShopCartFood.length">
							包包空空如也~
						</view>
						<block v-for="(food,i) in currentShopCartFood" :key="i">
							<uni-swipe-action>
								<uni-swipe-action-item :right-options="options" @click="swipeActionHandler(food)">
									<shop-food :food="food" :showAddOrNum="true" @changeCount="handlerChangeCount">
									</shop-food>
								</uni-swipe-action-item>
							</uni-swipe-action>
						</block>
					</scroll-view>
				</view>
			</uni-popup>
		</view>
	</view>


	</view>
</template>

<script>
	import {
		mapState
	} from 'vuex'
	import {
		mapMutations
	} from 'vuex'
	import {
		mapGetters
	} from 'vuex'
	export default {
		data() {
			return {
				//当前设备可用高度
				wh: 0,
				//店铺头信息
				shopInfo: {},
				//所有分类和菜品
				cateFoodsList: [],
				//点击不同的分类,展示不同的菜品列表
				foodsList: [],
				//评论列表
				commentList: [],
				scrollTop: 0,
				active: 0,
				items: ['order dishes', 'Reviews of our shop'],
				current: 0,

				//是否展示弹出层, 添加这个是为了重复点击图标的时候可以打开关闭弹出层
				isShowPopup: false,

				//背包的总价
				// currentShopTotalPrice: 1993,

				//滑动删除配置
				options: [{
					text: 'delete', // 显示的文本内容
					style: {
						backgroundColor: '#ff0000' // 按钮的背景颜色
					}
				}],
			};
		},
		onLoad(opt) {
			const info = uni.getSystemInfoSync()
			this.wh = info.windowHeight - 50
			const shopId = opt.shopId
			this.getFoods(shopId)
			this.getShopInfo(shopId)
			this.getCommentList(shopId)
		},
		computed: {
			...mapState('m_cart', ['cart']),
			...mapState('m_user', ['token']),
			...mapGetters('m_cart', ['totalCart']),

			//购物车中,属于当前店铺的菜品数量
			currentShopCartTotal() {
				const currentShopCart = this.cart.filter(x => x.shopId === this.shopInfo.id)
				let c = 0
				currentShopCart.forEach(food => c += food.count)
				return c
			},
			//购物车中,属于当前店铺的所有菜
			currentShopCartFood() {
				const res = this.cart.filter(x => x.shopId === this.shopInfo.id)
				return res
			},
			//购物车中,属于当前店铺的所有菜的价格总计
			currentShopTotalPrice(){
				//最后*100,是因为组件的单位是分,不是元
				return this.currentShopCartFood.reduce(
				(total,item)=>total += item.count * item.price,0)*100
			}

		},
		methods: {
			...mapMutations('m_cart', ['addToCart', 'removeFoodById', 'updateFoodCount','updateCheckOutList']),
			...mapMutations('m_user', ['updateRedirectInfo']),
			
			async getFoods(shopId) {
				let {
					data: res
				} = await uni.$http.get(`/shop/foods/${shopId}`)
				console.log("cateRes", res)
				if (res.code === 200) {
					this.cateFoodsList = res.data
					//初始化展示的菜品列表,第一个默认是 "今日推荐"分类
					this.foodsList = res.data[0].foods
				}
			},
			async getShopInfo(shopId) {
				let {
					data: res
				} = await uni.$http.get(`/shop/info/${shopId}`)
				if (res.code === 200) {
					// console.log("shopinfo", res.data)
					this.shopInfo = res.data
				}
			},
			async getCommentList(shopId) {
				let {
					data: res
				} = await uni.$http.get(`/comment/list/${shopId}`)
				if (res.code === 200) {
					console.log("commentlist", res.data)
					this.commentList = res.data
				}
			},
			//点击不同分类
			activeChanged(i) {
				this.active = i
				// 为二级分类列表重新赋值
				this.foodsList = this.cateFoodsList[i].foods
				//滚动条重置
				this.scrollTop = this.scrollTop === 0 ? 1 : 0
			},
			//点击不同标签页 菜品 评价
			onClickItem(e) {
				if (this.current != e.currentIndex) {
					this.current = e.currentIndex
				}
			},
			//开关弹出层
			showPopup() {
				if (this.isShowPopup) {
					this.$refs.popup.close()
					this.isShowPopup = false
					return
				}
				this.$refs.popup.open('bottom')
				console.log(this.cart)
				console.log("current shop cart count", this.currentShopCartTotal)
				console.log("current shop cart food", this.currentShopCartFood)
				this.isShowPopup = true
			},
			//去菜品详情页
			goToDetail(id) {
				uni.navigateTo({
					url: '../food_detail/food_detail?foodId=' + id + '&shopId=' + this.shopInfo.id
				})
			},
			onSubmit(){
				// 是否登录了
				if (!this.token) return this.delayNavigate()
				//跳转到登录页面
				console.log('在店铺提交订单')
				
				//把当前店铺的包包里面的菜,放到结算的list里面去
				//结算成功之后,要移除购物车里面已经结算的菜
				this.updateCheckOutList(this.currentShopCartFood)
				uni.navigateTo({
					url:'../checkout/checkout'
				})

			},
			//滑动删除
			swipeActionHandler(food) {
				console.log('swipeActionHandler', food.id)
				this.removeFoodById(food.id)
			},
			//加减数量
			handlerChangeCount(food) {
				console.log('店铺页面,测试菜单修改数量,输出food',food)
				this.updateFoodCount(food)
			},
			delayNavigate() {
				// 把 data 中的秒数重置成 3 秒
				this.seconds = 3
				this.showTips(this.seconds)
				this.timer = setInterval(() => {
					this.seconds--
			
					if (this.seconds <= 0) {
						clearInterval(this.timer)
						uni.switchTab({
							url: '/pages/my/my',
							// 页面跳转成功之后的回调函数
							success: () => {
								// 调用 vuex 的 updateRedirectInfo 方法，把跳转信息存储到 Store 中
								this.updateRedirectInfo({
									// 跳转的方式
									openType: 'shopPage',
									// 从哪个页面跳转过去的
									from: '/subpkg/food_list/food_list?shopId='+this.shopInfo.id
								})
							}
						})
						return
					}
			
					this.showTips(this.seconds)
				}, 1000)
			},
			// 展示倒计时的提示消息
			showTips(n) {
				// 调用 uni.showToast() 方法，展示提示消息
				uni.showToast({
					// 不展示任何图标
					icon: 'none',
					// 提示的消息
					title: '请登录后再结算！' + n + ' 秒后自动跳转到登录页',
					// 为页面添加透明遮罩，防止点击穿透
					mask: true,
					// 1.5 秒后自动消失
					duration: 1500
				})
			},
		},
	}
</script>

<style lang="scss">
	.content {
		// 给页面外层的容器，添加 50px 的内padding，
		// 防止页面内容被底部的商品导航组件遮盖
		padding-bottom: 50px;
	}

	.nav-cart-bar {
		// 为商品导航组件添加固定定位
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
		z-index: 100;
	}

	.nav-popup {
		.nav-popup-items {
			padding-bottom: 60px;
		}
	}

	.reply {
		background-color: #f5f7f4;
	}

	.name-class {
		font-weight: bold;
	}

	.divider {
		// background: #E0E3DA;
		width: 100%;
		height: 5rpx;
		margin: 10rpx 0;
	}

	.cate-lv3-list {
		display: flex;
		flex-wrap: wrap;

		.cate-lv3-item {
			width: 33.33%;
			margin-bottom: 10px;
			display: flex;
			flex-direction: column;
			align-items: center;

			image {
				width: 60px;
				height: 60px;
			}

			text {
				font-size: 12px;
			}
		}
	}

	.scroll-view-container {
		display: flex;


		.left-scroll-view {
			width: 120px;

			.left-scroll-view-item {
				background-color: #F7F7F7;
				line-height: 60px;
				text-align: center;
				font-size: 12px;
				border-radius: 10px;

				&.active {
					background-color: #FFFFFF;
					position: relative;

					&::before {
						content: ' ';
						display: block;
						width: 3px;
						height: 30px;
						background-color: #E67E22;
						position: absolute;
						top: 50%;
						left: 0;
						transform: translateY(-50%);
					}
				}
			}
		}
	}
</style>
