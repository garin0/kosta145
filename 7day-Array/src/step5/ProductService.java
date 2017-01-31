package step5;

public class ProductService {

	public ProductVO findProductVOByName(ProductVO[] pa, String name) {
		ProductVO pvo = null;
		for (int i = 0; i < pa.length; i++) {
			if (pa[i].getName().equals(name)) {
				pvo = pa[i];
				break;
			}
		}
		return pvo;
	}

	public ProductVO[] findProductListByMaker(ProductVO[] pa, String maker) {
		int cnt = 0;
		for (int i = 0; i < pa.length; i++) {
			if (pa[i].getMaker().equals(maker)) {
				cnt++;
			}
		}
		ProductVO[] reList = null;
		if (cnt > 0) {
			reList = new ProductVO[cnt];
			int index = 0;
			for (int i = 0; i < pa.length; i++) {
				if (pa[i].getMaker().equals(maker))
					reList[index++] = pa[i];
			}
		}
		return reList;
	}
}
