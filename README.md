# Kodetak


  Deniz Karakay - Özgür Kara - Ahmet Akman \
![Image](https://github.com/dkarakay/Kodetak/blob/master/logos/metu_eee.png){: width=50% }
![Image](https://github.com/dkarakay/Kodetak/blob/master/logos/rk1Zezjt_400x400.jpg){: width=150 height=100 style="float:right; padding:16px"}

# Fikir Nasıl Çıktı

  Görme engelli insanların görme duyusuna dair his geliştirme ile ilgili araştırmaları incelerken dikkatimizi çeken görüntülerin farklı yöntem ve formlarda ses ile görme engelli bireye aktarılması düşüncesinden yola çıkarak bireyin ortamda kendisine yaklaşmakta olan nesneler ile ilgili hissiyatını geliştirmeyi düşündük. 

# Nasıl Geliştirdik

  Bazı ümit verici akademik makalelerde[1] de bahsi geçen gerçek zamanlı nesne tanıma ile bireyin bilgilendirmesi amacı üzerinde projemizi inşa ettik. Genel bağlamda bireyin elindeki kamera ile aldığı görüntülerin işlenerek yine bireye farklı yönlerden gelen sesler olarak yansıtılması projemizi açıklıyor.
  
# Teknik Olarak

  Görme engelli bireyin elinde ya da görüş alanına sabitlenmiş durumdaki cep telefonunun kamerasından alınan görüntü yerel ağ üzerinden veriyi işleyecek olan bilgisayara gönderilir. Bilgisayardaki yazılımımız, yerel ağdan aldığı görüntüyü bilgisayarlı görü ve makine öğrenmesi algoritmalarının yardımıyla işleyerek bireyin görüş alanındaki cisimlere sınıflandırma ve konumlandırma işlemi yapar. Bireyin görüş alanındaki cisimler işlenirken bireye yaklaşan cisimler ayırt edilerek bireye göre konumu ve hangi cisim olduğu bilgisi cep telefonuna iletilir. Cep telefonundaki mobil uygulamamız bu verilere uygun olarak sağ veya sol kulaklıktan cismin boyutu ile orantılı olarak belirli desibel düzeyinde bireye sesler verir. Böylece görme engelli birey yolda yürürken kendisine yaklaşmakta olan cisimleri bir başkasının yardımı olmaksızın belli ölçüde tanıma yetisine sahip olur.

\
  Projemizde yazılımlarımız biribirinden farklı altsistemler üzerinde ve dillerde yazılmıştır. Mobil uygulmamamız Java dilinde ve Flask Framework[2] kullanılarak oluşturulmuştur. Görüntü işleme işlemlerimiz yapıldığı bilgisayarımızda Python dilinde görüntü işlemek için OpenCV[3] bilgisayarlı görü kütüphanesi kullanılmıştır. Nesne tanımlama işlemlerimiz YOLOv3[4] algoritmasını (geri planda TensorFlow[5] çalışıyor) kullandık.
![Image](https://github.com/dkarakay/Kodetak/blob/master/logos/flasj.jpg = 250x250)
![Image](https://github.com/dkarakay/Kodetak/blob/master/logos/python.jpg = 250x250)
![Image](https://github.com/dkarakay/Kodetak/blob/master/logos/android-studio-integrated-development-environment-software-build-intellij-idea-studio.jpg = 250x250)
![Image](https://png2.cleanpng.com/sh/c711520b30039fbd3e3b3f1e8a896a5c/L0KzQYm4UcI2N5D6gpH0aYP2gLBuTf9xbZ9ojp9ybXHqdX73kv9kbaR4gdDwLXPyfcH8lPVzNaR0fuZCYYLoPbTzigAuaaN5RadrZnHmdIfog8IzbpMARqkDN0izSYO3UcU1O2I7S6Q9Mke8RYW1kP5o/kisspng-opencv-image-processing-computer-software-clip-art-5bfacd6ac22fb9.7878092015431632427954.png = 250x250)
 
 # Daha da İleri!
\
  Bireyin kullanıdığı cep telefonunu tüm işlemlerin yapıldığı yer haline getirerek ürün bağımlılığını azaltmak ürünün cazibesini ve zor koşullarda çalışma doğruluğunu artıracaktır.
\
  Görüntü üzerinden tanımlamada görüntü derinliğini yakınsayan makine öğrenmesi algoritmalarının mobil cihazda çalıştırma fikri bireyin ortam hissiyatını artırmada yardımcı olabilir.

# Kaynaklar
\
[1]https://www.researchgate.net/publication/312593672_Let_Blind_People_See_Real-Time_Visual_Recognition_with_Results_Converted_to_3D_Audio
\
[2] https://www.palletsprojects.com/p/flask/
\
[3] https://opencv.org 
\
[4] https://pjreddie.com/darknet/yolo/ 
\
[5] https://www.tensorflow.org/ 
\

 
