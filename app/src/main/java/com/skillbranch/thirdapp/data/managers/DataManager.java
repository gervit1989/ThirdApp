package com.skillbranch.thirdapp.data.managers;

import android.content.Context;
import android.graphics.Typeface;

import com.skillbranch.thirdapp.data.storage.models.ProductDTO;
import com.skillbranch.thirdapp.utils.SkillBranchApplication;

import java.util.ArrayList;
import java.util.List;


public class DataManager {
    /**
     * Отслеживание
     */
    private static final String TAG = "DataManager";

    /**
     * Экземпляр класса
     */
    private static DataManager INSTANCE = null;

    /**
     * Доступ к пользовательским значениям
     */
    private PreferencesManager mPreferencesManager;

    /**
     * Контекст приложения
     */
    private Context mContext;
    private static Typeface fontRegular;
    private static Typeface fontBook;

    private List<ProductDTO> mMockProductList;

    /**
     * Конструктор
     */
    public DataManager() {
        generateMockData();
        //- Сохранение настроек приложения
        this.mPreferencesManager = new PreferencesManager();

        //- Контекст приложения
        this.mContext = SkillBranchApplication.getContext();
        this.fontBook = SkillBranchApplication.getCustomBookFont();
        this.fontRegular = SkillBranchApplication.getCustomRegularFont();
    }


    /**
     * Получение единственного экземпляра
     * @return Менеджер
     */
    public static DataManager getINSTANCE(){
        if (INSTANCE==null){
            INSTANCE = new DataManager();
        }
        return  INSTANCE;
    }

    /**
     * Контекст приложения
     * @return контекст
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * Доступ к пользовательским значениям
     * @return Менеджер пользовательских значений
     */
    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }


    public static Typeface getCustomRegularFont() {
        return fontRegular;
    }

    public static Typeface getCustomBookFont() {
        return fontBook;
    }

    public ProductDTO getProductById(int product_id) {
        for (int i = 0; i < mMockProductList.size(); i++) {
            if (mMockProductList.get(i).getId()==product_id){
                return mMockProductList.get(i);
            }
        }
        return null;
    }

    public List<ProductDTO> getProductList(){
        return mMockProductList;
    }

    public void updateProduct(ProductDTO product) {
        for (int i = 0; i < mMockProductList.size(); i++) {
            if (mMockProductList.get(i).getId()==product.getId()){
                mMockProductList.set(i, product);
                break;
            }
        }
    }

    private void generateMockData(){
        mMockProductList = new ArrayList<>();
        mMockProductList.add(new ProductDTO(1, "test 1", "imageUrl", "description 1 description 1 description 1 description 1 description 1 ", 100, 1));
        mMockProductList.add(new ProductDTO(2, "test 2", "imageUrl", "description 2 description 2 description 2 description 2 description 2 ", 150, 2));
        mMockProductList.add(new ProductDTO(3, "test 3", "imageUrl", "description 3 description 3 description 3 description 3 description 3 ", 200, 3));
        mMockProductList.add(new ProductDTO(4, "test 4", "imageUrl", "description 4 description 4 description 4 description 4 description 4 ", 250, 4));
        mMockProductList.add(new ProductDTO(5, "test 5", "imageUrl", "description 5 description 5 description 5 description 5 description 5 ", 300, 5));
        mMockProductList.add(new ProductDTO(6, "test 6", "imageUrl", "description 6 description 6 description 6 description 6 description 6 ", 350, 6));
        mMockProductList.add(new ProductDTO(7, "test 7", "imageUrl", "description 7 description 7 description 7 description 7 description 7 ", 400, 7));
        mMockProductList.add(new ProductDTO(8, "test 8", "imageUrl", "description 8 description 8 description 8 description 8 description 8 ", 450, 8));
        mMockProductList.add(new ProductDTO(9, "test 9", "imageUrl", "description 9 description 9 description 9 description 9 description 9 ", 500, 9));
        mMockProductList.add(new ProductDTO(10, "test 10", "imageUrl", "description 10 description 10 description 10 description 10 description 10 ", 550, 10));
    }
}
