<template>
    <view class="food-container" >
        <!-- Left-side photo -->
        <view class="food-left">
             <radio :checked="food.state" @click="clickRadio" color="#E67E22" v-if="showRadio"></radio>
            <!-- <view class="food-pic"> -->
                <image :src="food.imgSrc" style="width: 200rpx; height: 200rpx;border-radius:10rpx;"></image>
            <!-- </view> -->
        </view>
        <!-- Right-side content -->
        <view class="food-right">

            <!-- Dish name -->
            <view class="food-name">
                {{food.name}}
            </view>

            <!-- Bottom of the dish, should include price and (add button + quantity selection button), depending on the situation -->
            <view class="food-btm">
                <!-- Price -->
                <view class="food-price">¥{{food.price}}</view>
                <!-- If showAddOrNum===true, display (add button + quantity selection button) -->
                <view v-if="showAddOrNum">
                    
                    <view class="food-add" @click.stop="addFood()" v-show="food.count==0">
                        <!-- Button to add to cart -->
                        <!-- <van-icon name="add-o" /> -->
                        <!-- <van-icon name="plus" /> -->
                        <van-button icon="plus" color="#E67E22" round size="mini" />
                    </view>
                    <view v-show="food.count>0">
                        <van-stepper 
                        :value="food.count" 
                        min="1" 
                        @change.stop="foodCountChange"
                        disable-input/>
                    </view>
                </view>
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
    export default {
        name: "shop-food",
        props: {
            food: {
                type: Object,
                default: {}
            },
            showAddOrNum:{
                type:Boolean,
                default:false
            },
            showRadio:{
                type:Boolean,
                default:false
            }
        },
        data() {
            return {

            };
        },
        methods:{
            ...mapMutations('m_cart', ['addToCart']),
            
            // Add dish to cart
            addFood(){
                console.log("food component addFood",this.food)
                const foodToCart = {
                    id:this.food.id,
                    name:this.food.name,
                    price:this.food.price,
                    count:1,
                    imgSrc:this.food.imgSrc,
                    shopId:this.food.shopId,
                    state:true
                }
                this.addToCart(foodToCart)
            },
            goToDetail() {
                uni.navigateTo({
                    url: '../food_detail/food_detail?foodId=' + this.food.id
                })
            },
            foodCountChange(e){
                // console.log('Component executed, testing menu quantity change e.detail',e.detail)
                // console.log('Component executed, testing menu quantity change, output food id ',this.food.id)
                this.$emit('changeCount',{
                    id:this.food.id,
                    // Convert to number type
                    count:+e.detail
                })
                // console.log(e.detail)
                // console.log(e)
            },
            clickRadio(){
                    this.$emit('radioChange', {
                      // Id
                      id: this.food.id,
                      // Toggle selection state
                      state: !this.food.state
                    })
            }
        }
    }
</script>

<style lang="scss">
    .food-container {
        display: flex;
        margin: 10rpx 0rpx 10rpx 20rpx;

        
        .food-left{
          margin-right: 5px;
          display: flex;
          justify-content: space-between;
          align-items: center;
        }

        .food-right {

            display: flex;
            flex: 1;
            flex-direction: column;
            justify-content: space-between;
            // flex-wrap: wrap;
            // justify-content: center;

            .food-name {
                font-size: 16px;
                font-weight: bold;
                padding-left: 10rpx;
    
            }

            .food-btm {
                display: flex;
                align-items: center;
                justify-content: space-between;
                
                .food-add{
                    padding-right: 20px;
                }
                
                .food-price {
                    padding-left: 10px;
                    font-size: 16px;
                    color: #c00000;
                }
            }
        }
    }
</style>

