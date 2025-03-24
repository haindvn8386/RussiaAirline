
![image](https://github.com/user-attachments/assets/fadb8089-9143-4290-a3c9-de38cdb1baab)


**Danh sách các bảng trong database Airline, bao gồm:**

aircrafts_data <br>
airports_data <br>
boarding_passes <br>
bookings <br>
flights <br>
seats <br>
ticket_flights <br>
tickets<br>
Đây là một schema điển hình cho hệ thống đặt vé máy bay. Tôi sẽ phân tích và chỉ ra mối quan hệ giữa các bảng dựa trên tên và ý nghĩa của chúng, vì đây là các bảng phổ biến trong hệ thống hàng không.
Nếu bạn có schema chi tiết (các cột và khóa), tôi có thể phân tích sâu hơn.

![image](https://github.com/user-attachments/assets/ead1e108-120f-4f7f-abf8-286c306fd5d2)

**1. Tổng quan về các bảng**
Dựa trên tên bảng, chúng thể suy ra ý nghĩa và vai trò của chúng:

**aircrafts_data**: Lưu thông tin về máy bay (mã máy bay, loại, phạm vi bay, v.v.).
**airports_data**: Lưu thông tin về sân bay (mã sân bay, tên, thành phố, v.v.).
**boarding_passes**: Lưu thông tin thẻ lên máy bay (số vé, số ghế, thời gian lên máy bay, v.v.).
**bookings**: Lưu thông tin đặt chỗ (mã đặt chỗ, ngày đặt, tổng tiền, v.v.).
**flights**: Lưu thông tin chuyến bay (mã chuyến bay, sân bay đi/đến, thời gian, máy bay, v.v.).
**seats**: Lưu thông tin ghế trên máy bay (mã máy bay, số ghế, loại ghế, v.v.).
**ticket_flights**: Bảng trung gian, liên kết vé và chuyến bay (mã vé, mã chuyến bay, giá vé, v.v.).
**tickets**: Lưu thông tin vé (mã vé, mã đặt chỗ, thông tin hành khách, v.v.).

**2. Mối quan hệ giữa các bảng**
Dựa trên ý nghĩa, tôi sẽ suy ra các mối quan hệ (relationships) giữa các bảng, thường được thiết lập qua khóa chính (primary key) và khóa ngoại (foreign key).

**a. bookings và tickets**
Mối quan hệ: Một đặt chỗ (bookings) có thể có nhiều vé (tickets) - Quan hệ 1:N (một-nhiều).
Khóa liên kết:
bookings có khóa chính là book_ref (mã đặt chỗ).
tickets có khóa ngoại là book_ref, tham chiếu đến bookings(book_ref).
Ý nghĩa: Một khách hàng đặt chỗ (booking) có thể mua nhiều vé cho nhiều hành khách.

**b. tickets và ticket_flights**
Mối quan hệ: Một vé (tickets) có thể liên quan đến nhiều chuyến bay (flights) qua bảng trung gian ticket_flights - Quan hệ N:N (nhiều-nhiều).
Khóa liên kết:
tickets có khóa chính là ticket_no (mã vé).
ticket_flights có khóa ngoại ticket_no tham chiếu tickets(ticket_no) và flight_id tham chiếu flights(flight_id).
Ý nghĩa: Một vé có thể bao gồm nhiều chuyến bay (ví dụ: chuyến bay nối chuyến), và một chuyến bay có thể có nhiều vé.

**c. flights và ticket_flights**
Mối quan hệ: Một chuyến bay (flights) có thể có nhiều vé, được liên kết qua ticket_flights - Quan hệ 1:N (một-nhiều) từ flights đến ticket_flights.
Khóa liên kết:
flights có khóa chính là flight_id (mã chuyến bay).
ticket_flights có khóa ngoại flight_id tham chiếu flights(flight_id).
Ý nghĩa: Bảng ticket_flights lưu thông tin chi tiết (như giá vé) cho từng vé trên từng chuyến bay.

**d. flights và aircrafts_data**
Mối quan hệ: Một chuyến bay (flights) được thực hiện bởi một máy bay (aircrafts_data) - Quan hệ N:1 (nhiều-một).
Khóa liên kết:
aircrafts_data có khóa chính là aircraft_code (mã máy bay).
flights có khóa ngoại aircraft_code tham chiếu aircrafts_data(aircraft_code).
Ý nghĩa: Mỗi chuyến bay được gán cho một máy bay cụ thể.

**e. flights và airports_data**
Mối quan hệ: Một chuyến bay (flights) có sân bay đi và sân bay đến, liên kết với airports_data - Quan hệ N:1 (nhiều-một) cho cả sân bay đi và đến.
Khóa liên kết:
airports_data có khóa chính là airport_code (mã sân bay).
flights có hai khóa ngoại:
departure_airport tham chiếu airports_data(airport_code).
arrival_airport tham chiếu airports_data(airport_code).
Ý nghĩa: Mỗi chuyến bay có sân bay khởi hành và sân bay đích.

**f. seats và aircrafts_data**
Mối quan hệ: Một máy bay (aircrafts_data) có nhiều ghế (seats) - Quan hệ 1:N (một-nhiều).
Khóa liên kết:
aircrafts_data có khóa chính là aircraft_code.
seats có khóa ngoại aircraft_code tham chiếu aircrafts_data(aircraft_code).
Ý nghĩa: Mỗi máy bay có danh sách ghế (ví dụ: 1A, 1B, v.v.).

**g. boarding_passes và ticket_flights**
Mối quan hệ: Một vé-chuyến bay (ticket_flights) có một thẻ lên máy bay (boarding_passes) - Quan hệ 1:1 (một-một).
Khóa liên kết:
ticket_flights có khóa chính là cặp (ticket_no, flight_id).
boarding_passes có khóa ngoại (ticket_no, flight_id) tham chiếu ticket_flights(ticket_no, flight_id).
Ý nghĩa: Mỗi vé trên một chuyến bay được cấp một thẻ lên máy bay (boarding pass) khi check-in.

**h. boarding_passes và seats**
Mối quan hệ: Một thẻ lên máy bay (boarding_passes) được gán cho một ghế (seats) - Quan hệ N:1 (nhiều-một).
Khóa liên kết:
seats có khóa chính là (aircraft_code, seat_no).
boarding_passes có khóa ngoại (aircraft_code, seat_no) tham chiếu seats(aircraft_code, seat_no).
Ý nghĩa: Mỗi thẻ lên máy bay chỉ định ghế cụ thể trên máy bay.
